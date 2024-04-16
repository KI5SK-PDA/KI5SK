package kiosk.service.order;

import card.service.PurchaseServiceImpl;
import card.service.dto.res.PurchaseResponse;
import common.vo.Money;
import kiosk.model.menu.Category;
import kiosk.model.menu.CategoryId;
import kiosk.model.menu.CategoryRepository;
import kiosk.model.menu.Menu;
import kiosk.model.store.StoreId;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.menu.dto.res.MenuInfo;
import kiosk.service.menu.dto.res.OptionMenuInfo;
import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;
import card.service.dto.PurchaseDTO;


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
            String storeId = parts[0];
            String categoryId = parts[1];
            String menuId = parts[2];

            // 해당 카테고리에서 메뉴 정보 조회
            List<MenuInfo> menuInfos = CategoryInfo.builder().build().getMenus();
            MenuInfo selectedMenu = null;
            Money menuPrice = null;
            for (MenuInfo menuInfo : menuInfos) {
                if (menuInfo.getId().equals(menuId)) {
                    selectedMenu = menuInfo;
                    // 메뉴 기본 가격
                    menuPrice = Money.of(selectedMenu.getOriginalPrice());
                } else selectedMenu = null;
            }

            // 옵션 가격 합산
            Money optionPriceTotal = Money.of(0);
            for (String optionMenuId : selectedMenuInfo.getOptionMenuIds()) {
                // 각 옵션 메뉴의 가격을 조회 (실제 구현에서는 옵션 메뉴 정보 조회 로직 필요)
                OptionMenuInfo optionMenu = findOptionMenuById(optionMenuId); // findOptionMenuById는 가정된 메소드입니다.
                optionPriceTotal = optionPriceTotal.add(Money.of(optionMenu.getPrice()));
            }

            // 메뉴 가격과 옵션 가격 합산
            Money totalMenuPrice = menuPrice.add(optionPriceTotal);

            // 총 금액에 메뉴 가격 추가
            totalPrice = totalPrice.add(totalMenuPrice);
        }

        // 2. 결제 요청 준비
        Date currentDate = new Date(); // 현재 날짜
        String storeName = "상점 이름"; // 상점 이름, 실제로는 요청 데이터나 설정에서 가져와야 할 수 있음
        PurchaseDTO purchaseDTO = new PurchaseDTO(
                orderRequest.getCardNumber(),
                orderRequest.getCardPassword(),
                totalPrice, // 계산된 총 금액
                currentDate,
                storeName);

        // 3. 결제 요청 및 응답 처리
        PurchaseResponse purchaseResponse = PurchaseServiceImpl.purchase(purchaseDTO);
        if (purchaseResponse.isSuccess()) {
            // 결제 성공 시 로직
            return OrderResponse.builder()
                    .success(true)
                    .message("주문 및 결제가 성공적으로 완료되었습니다.")
                    .amountOfPurchase() // 필요한 경우 구매 금액을 설정)
                    .build();
        } else {
            // 결제 실패 시 로직
            return OrderResponse.builder()
                    .success(false)
                    .message("주문 실패: " + purchaseResponse.getMessage())
                    .amountOfPurchase() // 필요한 경우 구매 금액을 설정)
                    .build();
        }

//    @Override
//    public OrderResponse orderMenus(OrderRequest orderRequest) {
//        // 1. 해당 오더의 메뉴 금액을 구하기
//        // 메뉴 아이디 = "스토어아이디"-"카테고리아이디"-기본아이디
//        // ex) 001-02-00001
//
//        // Category category = categoryRepository.findById("001-02");
//        // getDiscountPrice() + optionMenu가격  => 총 가격
//
//        // 주문된 메뉴 ID 목록 추출
//        List<OrderRequest.SelectedMenuInfo> menus = orderRequest.getMenus();
//
//        // 총 가격 초기화
//        Money totalPrice = Money.of(0);
//
//        // 주문된 메뉴 ID 목록을 반복 처리
//        for (OrderRequest.SelectedMenuInfo menu : menus) {
//
//            String[] parts = menu.getMenuId().split("-");
//
//            String storeId = parts[0]; // (스토어)
//            String categoryId = parts[1]; // (카테고리)
//            String menuId = parts[2]; // (메뉴)
//
//            // 1. 카테고리 아이디 -> 카테고리를 조회 => List<MenuInfo
//            List<MenuInfo> menuInfos = CategoryInfo.builder().build().getMenus();
//
//            MenuInfo selectedMenu = null;
//            // 2. List에서 menuId로 menu를 조회 => menu의 정보를 얻는다
//            for(MenuInfo menuInfo:menuInfos){
//                if(menuInfo.getId().equals(menuId)){
//                    selectedMenu = menuInfo;
//
////                    // 옵션 메뉴 가격을 합산하기 위한 변수 초기화
////                    int optionMenuTotalPrice = 0;
////                    // 주문된 옵션 메뉴 ID 목록을 반복 처리하며 각 옵션 메뉴의 가격을 합산
////                    for (String optionMenuId : selectedMenu.getId()) {
////                        // 옵션 메뉴 정보를 조회 (여기서는 옵션 메뉴 정보 조회 방법을 가정함)
////                        OptionMenuInfo optionMenu = findOptionMenuById(optionMenuId);
////                        // 옵션 메뉴의 가격을 합산
////                        optionMenuTotalPrice += optionMenu.getPrice();
////                    }
////                    // 선택된 메뉴의 할인 가격이 존재하면 그 값을 사용하고, 그렇지 않으면 원래 가격을 사용
////                    int menuPrice = selectedMenu.getDiscountPrice().orElse(selectedMenu.getOriginalPrice());
//                }
//                else selectedMenu = null;
//            }
//            // 3. 수량, 옵션, 할인 전부 고려해서 결제 금액 계산
//            Money money = Money.of(Integer.parseInt(String.valueOf(selectedMenu.getDiscountPrice().get()))); //+ optionMenuTotalPrice)));
//
//        }
//
//
//        // 2. 해당 금액으로 카드 결제 요청하기
//        // 결제 요청을 위한 PurchaseDTO 생성
//        PurchaseDTO purchaseDTO = new PurchaseDTO(orderRequest.getCardNumber(), orderRequest.getCardPassword(), money, );
//
//        // 결제 요청
//        PurchaseResponse purchaseResponse = PurchaseServiceImpl.purchase(purchaseDTO);
//
//        // 결제 응답을 기반으로 OrderResponse 생성 및 반환
//        if (purchaseResponse.isSuccess()) {
//            // 결제 성공 시 로직
//            OrderResponse orderResponse = new OrderResponse();
//            orderResponse.setSuccess(true);
//            orderResponse.setMessage("주문 및 결제가 성공적으로 완료되었습니다.");
//            return orderResponse;
//        } else {
//            // 결제 실패 시 로직
//            OrderResponse orderResponse = new OrderResponse();
//            orderResponse.setSuccess(false);
//            orderResponse.setMessage("주문 실패: " + purchaseResponse.getMessage());
//            return orderResponse;
//        }
//
//
//        // 3. 최종적으로 결제된 금액 리턴해주기
//
//
//        // 4. 만약 잔고 부족시 Exception 발생
//
//        return null;
    }
}
