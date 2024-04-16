package kiosk.service.order;

import card.service.PurchaseServiceImpl;
import card.service.dto.req.PurchaseRequest;
import card.service.dto.res.PurchaseResponse;
import common.vo.Money;
import kiosk.model.menu.*;
import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;


import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final CategoryRepository categoryRepository;

    private OrderServiceImpl() {
        this.categoryRepository = CategoryRepository.getInstance();
    }

    public static OrderServiceImpl newInstance() {
        return new OrderServiceImpl();
    }

    @Override
    public OrderResponse orderMenus(OrderRequest orderRequest) {
        // 1. 메뉴 금액 계산 로직 개선
        Money totalPrice = Money.of(0); // 총 금액 초기화

        for (OrderRequest.SelectedMenuInfo selectedMenuInfo : orderRequest.getMenus()) {

            String[] parts = selectedMenuInfo.getMenuId().split("-");
            String categoryId = parts[0]+"-"+parts[1];

            // 해당 카테고리에서 메뉴 정보 조회
            Category category = categoryRepository.findById(CategoryId.of(categoryId))
                    .orElseThrow(() -> new RuntimeException("Can't find Category"));

            for(Menu menu: category.getMenus()) {
                if(menu.getId().equals(MenuId.of(selectedMenuInfo.getMenuId()))) {
                    if (menu.getDiscounter().isEmpty()) {
                        totalPrice = totalPrice.add(menu.getOriginalPrice());
                    } else {
                        totalPrice = totalPrice.add(menu.getDiscountPrice());
                    }
                    totalPrice = totalPrice.add(getSelectedOptionTotalPrice(menu, selectedMenuInfo.getSelectedOptionIds()));
                }
            }

            System.out.println(totalPrice);
        }

        // 2. 결제 요청 준비
        Date currentDate = new Date();
        String storeName = orderRequest.getStore();
        PurchaseRequest purchaseRequest = new PurchaseRequest(
                orderRequest.getCardNumber(),
                orderRequest.getCardPassword(),
                totalPrice,
                currentDate,
                storeName
        );

        // 3. 결제 요청 및 응답 처리
        PurchaseServiceImpl purchaseService = PurchaseServiceImpl.getInstance();
        PurchaseResponse purchaseResponse = purchaseService.purchase(purchaseRequest);
        if (purchaseResponse.isSuccess()) {
            // 결제 성공 시 로직
            return OrderResponse.builder()
                    .success(true)
                    .message("주문 및 결제가 성공적으로 완료되었습니다.")
                    .amountOfPurchase(purchaseResponse.getPurchaseDTO().getMoney().toInt())
                    .amountOfOrder(totalPrice.toInt())
                    .build();
        } else {
            // 결제 실패 시 로직
            return OrderResponse.builder()
                    .success(false)
                    .message("주문 실패: " + purchaseResponse.getMessage())
                    .build();
        }
    }

    private Money getSelectedOptionTotalPrice(Menu menu, List<String> selectedOptionIds) {
        Money totalPrice = Money.of(0);
        for(OptionGroupMenu optionGroup : menu.getOptionGroups()) {
            for (OptionMenu optionMenu: optionGroup.getAllOptionMenus()) {
                if(selectedOptionIds.contains(optionMenu.getId())) {
                    totalPrice = totalPrice.add(optionMenu.getPrice());
                }
            }
        }
        return totalPrice;
    }
}
