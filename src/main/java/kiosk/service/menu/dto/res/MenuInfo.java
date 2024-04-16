package kiosk.service.menu.dto.res;

import kiosk.model.menu.Menu;
import kiosk.model.menu.OptionGroupMenu;
import kiosk.model.menu.OptionMenu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<String> optionGroupIds;
    private Map<String, List<String>> optionMenuIdsMap;

    public static MenuInfo from(Menu menu){
        List<String> optionGroupIds = menu.getOptionGroups().stream()
                .map(OptionGroupMenu::getId)
                .collect(Collectors.toList());

        Map<String, List<String>> optionMenuIdsMap = menu.getOptionGroups().stream()
                .collect(Collectors.toMap(
                        OptionGroupMenu::getId,
                        optionGroupMenu -> optionGroupMenu.getAllOptionMenus().stream()
                                .map(OptionMenu::getId)
                                .collect(Collectors.toList())
                ));

        return MenuInfo.builder()
                .id(menu.getId().toString())
                .name(menu.getName())
                .originalPrice(menu.getOriginalPrice()==null? null: menu.getOriginalPrice().toInt())
                .discountPrice(Optional.ofNullable(menu.getDiscountPrice() == null? null: menu.getDiscountPrice().toInt()))
                .discountInfo(Optional.ofNullable(menu.getDiscountInfo()))
                .optionGroupIds(optionGroupIds)
                .optionMenuIdsMap(optionMenuIdsMap)
                .build();
    }
}
