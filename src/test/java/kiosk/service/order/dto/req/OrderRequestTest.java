package kiosk.service.order.dto.req;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Order Request DTO 테스트")
class OrderRequestTest {
    @DisplayName("생성 테스트")
    @Test
    void createRequest() {
        OrderRequest request = OrderRequest.builder()
            .cardNumber("123")
            .cardPassword("adfjkfdjk")
            .menus(Arrays.asList(
                OrderRequest.SelectedMenuInfo.builder()
                    .menuId("id")
                    .quantity(12321)
                    .selectedOptions(Arrays.asList("1","2","3"))
                    .build()
            ))
            .build();

        for(OrderRequest.SelectedMenuInfo menu: request.getMenus()) {
            System.out.println(menu.getMenuId());
        }
    }
}