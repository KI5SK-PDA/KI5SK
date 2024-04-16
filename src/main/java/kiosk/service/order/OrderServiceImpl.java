package kiosk.service.order;

import kiosk.service.menu.CategoryRepository;
import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;

public class OrderServiceImpl implements OrderService {
    // private final CategoryRepository categoryRepository;
    private final CategoryRepository categoryRepository;

    private OrderServiceImpl() {
        // this.categoryRepository = CategoryRepository.getInstance();
        this.categoryRepository = CategoryRepository.getInstance();
    }

    public static OrderServiceImpl newInstance() {
        return new OrderServiceImpl();
    }

    @Override
    public OrderResponse orderMenus(OrderRequest orderRequest) {
        // 1. 해당 오더의 메뉴 금액을 구하기
        // 메뉴 아이디 = "스토어아이디"-"카테고리아이디"-기본아이디
        // ex) 001-02-00001

        // Category category = categoryRepository.findById("001-02");
        // getDiscountPrice() + optionMenu가격  => 총 가격
        Category category = categoryRepository.fintById();

        // 2. 해당 금액으로 카드 결제 요청하기


        // 3. 최종적으로 결제된 금액 리턴해주기

        // 4. 만약 잔고 부족시 Exception 발생

        return null;
    }
}
