package kiosk.service.order.dto.req;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderRequest {
    private String cardNumber;
    private String cardPassword;
    private List<SelectedMenuInfo> menus;


    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    static class SelectedMenuInfo {
        private String menuId;
        private int quantity;
        private List<String> selectedOptions;
    }
}


