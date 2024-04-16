package kiosk.model.menu;

import kiosk.model.menu.CategoryId;
import kiosk.model.menu.Menu;
import kiosk.model.store.StoreId;
import common.vo.Money;
import kiosk.model.discounter.Discounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@DisplayName("메뉴 테스트")
class MenuTest {

    @DisplayName("생성 테스트")
    @Test
    void createMenu() {

        Optional<Discounter> discounter = Optional.empty();
        StoreId storeId = StoreId.of(1);
        CategoryId categoryId = CategoryId.of(storeId, 2);

        OptionMenu optionMenu = OptionMenu.create("옵션1", Money.of(1000));
        OptionGroupMenu group = OptionGroupMenu.create("그룹1");
        group.updateOptionMenus(optionMenu);

        System.out.println(group.getAllOptionMenus());

        Menu menu = Menu.create("TestMenu", Money.of(10000), discounter, categoryId);
        Menu menu1 = Menu.create("hello", Money.of(10000), discounter,categoryId );

        menu.updateOptionGroups(group);


        System.out.println(menu.getId());
        System.out.println(menu1.getId());
        System.out.println(menu.getAllOptionMenus());


    }
}
