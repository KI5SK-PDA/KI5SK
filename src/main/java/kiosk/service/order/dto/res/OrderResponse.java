package kiosk.service.order.dto.res;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class OrderResponse {
    private boolean success;
    private String message;
    private int amountOfPurchase; // 구매금액(할인 적용)
    private int amountOfOrder; // 주문금액(할인 미적용)
}
