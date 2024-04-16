package kiosk.service.menu.dto.res;

import kiosk.model.menu.Menu;
import kiosk.model.menu.OptionGroupMenu;
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
    private Map<String, String> optionGroupMap;
    private Map<String, List<OptionMenuInfo>> optionMenuMap;

    public static MenuInfo from(Menu menu){
        Map<String, String> optionGroupMap = menu.getOptionGroups().stream()
                .collect(Collectors.toMap(
                        OptionGroupMenu::getId,
                        OptionGroupMenu::getName
                ));

        Map<String, List<OptionMenuInfo>> optionMenuMap = menu.getOptionGroups().stream()
                .collect(Collectors.toMap(
                        optionGroupMenu -> optionGroupMenu.getId().toString(),
                        optionGroupMenu -> optionGroupMenu.getAllOptionMenus().stream()
                                .map(optionMenu -> new OptionMenuInfo(optionMenu.getId(), optionMenu.getName(), optionMenu.getPrice().toInt()))
                                .collect(Collectors.toList())
                ));

        return MenuInfo.builder()
                .id(menu.getId().toString())
                .name(menu.getName())
                .originalPrice(menu.getOriginalPrice()==null? null: menu.getOriginalPrice().toInt())
                .discountPrice(Optional.ofNullable(menu.getDiscounter().isEmpty()? null: menu.getDiscountPrice().toInt()))
                .discountInfo(Optional.ofNullable(menu.getDiscountInfo()))
                .optionGroupMap(optionGroupMap)
                .optionMenuMap(optionMenuMap)
                .build();
    }
}
