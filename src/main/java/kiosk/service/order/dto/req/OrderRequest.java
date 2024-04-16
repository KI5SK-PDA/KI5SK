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
    private String store;
    private List<SelectedMenuInfo> menus;


    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class SelectedMenuInfo {
        public String menuId;
        public int quantity;
        public List<String> selectedOptionIds;
    }

}


