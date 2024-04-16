package kiosk.service.order;

import card.service.CardService;
import kiosk.model.menu.Menu;
import kiosk.model.order.Order;
import kiosk.model.order.OrderRepository;
import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;
import kiosk.service.store.StoreService;

public class OrderServiceImpl implements OrderService {
    private final CardService cardService;
    private final StoreService storeService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CardService cardService, StoreService storeService, OrderRepository orderRepository) {
        this.cardService = cardService;
        this.storeService = storeService;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse OrderMenus(OrderRequest orderRequest) {
        // 1. 카드 정보 조회
        CardInfo cardInfo = cardService.getCardInfo(orderRequest.getCardNumber(), orderRequest.getCardPassword());

        // 2. 카드 할인 적용 (필요한 경우)
        if (cardInfo != null && cardInfo.isDiscountApplicable()) {
            applyCardDiscount(orderRequest, cardInfo);
        }

        // 3. 주문 생성 및 저장
        Order order = createOrder(orderRequest);
        orderRepository.save(order);

        // 4. 주문 응답 생성 및 반환
        return buildOrderResponse(order, cardInfo, store);
    }

    private Order createOrder(OrderRequest orderRequest) {
        // 주문 정보 생성 로직 구현 (orderRequest, 카드 할인 정보 등 활용)
        List<Menu> orderedMenus = // 주문된 메뉴 목록 추출
                Money totalPrice = // 총 가격 계산 (카드 할인 적용 포함)
        return new Order(orderedMenus, totalPrice);
    }

    private OrderResponse buildOrderResponse(Order order, CardInfo cardInfo, Store store) {
        // 주문 응답 DTO 생성 로직 구현 (주문 정보, 카드 정보, 스토어 정보 활용)
        List<OrderResponse.SelectedMenuInfo> responseMenus = convertMenusToResponseFormat(order.getMenus());
        Optional<OrderResponse.DiscountInfo> discountInfo = Optional.empty();
        if (cardInfo != null && cardInfo.isDiscountApplied()) {
            discountInfo = Optional.of(new OrderResponse.DiscountInfo(/* ... */));
        }
        return OrderResponse.builder()
                .orderId(order.getId().toString()) // 주문 ID 설정
                .originalPrice(order.getTotalPrice().getAmount()) // 주문 원가 설정
                .menus(responseMenus) // 주문 메뉴 목록 설정
                .discountInfo(discountInfo) // 할인 정보 설정 (필요한 경우)
                .finalPrice(order.getTotalPrice().getAmount()) // 최종 결제 가격 설정
                .build();
    }

    private List<OrderResponse.SelectedMenuInfo> convertMenusToResponseFormat(List<Menu> menus) {
        // 주문된 메뉴 정보를 OrderResponse.SelectedMenuInfo 형식으로 변환
        return menus.stream()
                .map(menu -> new OrderResponse.SelectedMenuInfo(menu.getId(), menu.getQuantity(), menu.getSelectedOptions()))
                .collect(Collectors.toList());
    }
}
