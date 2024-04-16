package kiosk.service.order.dto.res;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Order Response DTO 테스트")
class OrderResponseTest {
    @DisplayName("생성 테스트")
    @Test
    void createResponse() {
        // Given
        String orderId = "ORD123";
        int originalPrice = 5000;
        List<OrderResponse.SelectedMenuInfo> menus = new ArrayList<>();
        OrderResponse.SelectedMenuInfo menuInfo = OrderResponse.SelectedMenuInfo.builder()
            .menuId("MENU001")
            .quantity(2)
            .selectedOptions(List.of("Option1", "Option2"))
            .build();
        menus.add(menuInfo);

        List<String> discountType = List.of("TimePercentage", "card");
        int discountAmount = 1000;
        double discountRate = 0.2;
        OrderResponse.DiscountInfo discountInfo = OrderResponse.DiscountInfo.builder()
            .discountType(discountType)
            .discountAmount(discountAmount)
            .discountRate(discountRate)
            .build();

        int finalPrice = 3000;

        // When
        OrderResponse orderResponse = OrderResponse.builder()
            .orderId(orderId)
            .originalPrice(originalPrice)
            .menus(menus)
            .discountInfo(Optional.of(discountInfo))
            .finalPrice(finalPrice)
            .build();

        // Then
        assertEquals(orderId, orderResponse.getOrderId());
        assertEquals(originalPrice, orderResponse.getOriginalPrice());
        assertEquals(menus, orderResponse.getMenus());
        assertTrue(orderResponse.getDiscountInfo().isPresent());
        assertEquals(discountInfo, orderResponse.getDiscountInfo().get());
        assertEquals(finalPrice, orderResponse.getFinalPrice());
    }
}