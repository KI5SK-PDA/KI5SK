package kiosk.service.order.dto.res;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderResponse {
    private boolean success;
    private String message;
    private int amountOfPurchase;
}
