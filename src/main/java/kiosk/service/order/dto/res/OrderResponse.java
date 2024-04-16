package kiosk.service.order.dto.res;

import lombok.*;

import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderResponse {
    private String orderId;
    private int originalPrice;
    private List<OrderResponse.SelectedMenuInfo> menus;
    private Optional<DiscountInfo> discountInfo;
    private int finalPrice;

    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    static class SelectedMenuInfo {
        private String menuId;
        private int quantity;
        private List<String> selectedOptions;
    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class DiscountInfo {
        private List<String> discountType;
        private int discountAmount;
        private double discountRate;
    }

}
