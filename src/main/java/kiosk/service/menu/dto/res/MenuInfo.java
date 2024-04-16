package kiosk.service.menu.dto.res;

import kiosk.model.menu.Menu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MenuInfo {
    private String id;
    private String name;
    private int originalPrice;
    private Optional<Integer> discountPrice;
    private Optional<String> discountInfo;

    public static MenuInfo from(Menu menu){
        return MenuInfo.builder()
                .id(menu.getId().toString())
                .name(menu.getName())
                .originalPrice(menu.getOriginalPrice()==null? null: menu.getOriginalPrice().toInt())
                .discountPrice(Optional.ofNullable(menu.getDiscountPrice() == null? null: menu.getDiscountPrice().toInt()))
                .discountInfo(Optional.ofNullable(menu.getDiscountInfo())).build();
    }
}
