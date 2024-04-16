package kiosk.service.order;

import common.vo.Money;
import kiosk.model.menu.Category;
import kiosk.model.menu.CategoryId;
import kiosk.model.menu.CategoryRepository;
import kiosk.model.menu.Menu;
import kiosk.model.store.StoreId;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.menu.dto.res.MenuInfo;
import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;

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
        // 1. 해당 오더의 메뉴 금액을 구하기
        // 메뉴 아이디 = "스토어아이디"-"카테고리아이디"-기본아이디
        // ex) 001-02-00001

        // Category category = categoryRepository.findById("001-02");
        // getDiscountPrice() + optionMenu가격  => 총 가격

        // 주문된 메뉴 ID 목록 추출
        List<OrderRequest.SelectedMenuInfo> menus = orderRequest.getMenus();

        // 총 가격 초기화
        Money totalPrice = Money.of(0);

        // 주문된 메뉴 ID 목록을 반복 처리
        for (OrderRequest.SelectedMenuInfo menu : menus) {

            String[] parts = menu.getMenuId().split("-");

            String storeId = parts[0]; // (스토어)
            String categoryId = parts[1]; // (카테고리)
            String menuId = parts[2]; // (메뉴)

            // 1. 카테고리 아이디 -> 카테고리를 조회 => List<MenuInfo
            List<MenuInfo> menuInfos = CategoryInfo.builder().build().getMenus();

            MenuInfo selectedMenu = null;
            // 2. List에서 menuId로 menu를 조회 => menu의 정보를 얻는다
            for(MenuInfo menuInfo:menuInfos){
                if(menuInfo.getId().equals(menuId)){
                    selectedMenu = menuInfo;
                }
                else selectedMenu = null;
            }
            // 3. menu의 정보를 가지고 계산한다
            Money money = Money.of(Integer.parseInt(selectedMenu.getDiscountInfo().get()));
            // 4. 수량 등 체크하고 총액을 계산해서 결제 요청을 보낸다

        }


        // 2. 해당 금액으로 카드 결제 요청하기


        // 3. 최종적으로 결제된 금액 리턴해주기

        // 4. 만약 잔고 부족시 Exception 발생

        return null;
    }
}
