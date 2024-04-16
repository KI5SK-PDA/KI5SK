package kiosk.service.menu;

import common.vo.Money;
import kiosk.model.discounter.AlwaysCashDiscounter;
import kiosk.model.discounter.Discounter;
import kiosk.model.discounter.TimePercentageDiscounter;
import kiosk.model.menu.*;
import kiosk.model.store.Store;
import kiosk.model.store.StoreRepository;
import kiosk.service.menu.dto.res.CategoryInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@DisplayName("MenuService 테스트")
public class MenuServiceGetTest {
    private final StoreRepository storeRepository = StoreRepository.getInstance();
    private final CategoryRepository categoryRepository = CategoryRepository.getInstance();
    private final MenuService menuService = MenuService.newInstance();

    @DisplayName("생성 테스트")
    @Test
    void testGetAllMenusByStoreId(){
        Store store = Store.create("가게1", Money.of(100000), "image");
        Category category1 = Category.create("카테고리1");
        Category category2 = Category.create("카테고리2");

        storeRepository.save(store);
        categoryRepository.create(category1, store.getId());
        categoryRepository.create(category2, store.getId());

        store.updateCategoryId(category1.getId());
        store.updateCategoryId(category2.getId());

        Optional<Discounter> discounter = Optional.of(AlwaysCashDiscounter.create(Money.of(2000)));
//        Optional<Discounter> discounter = Optional.of(TimePercentageDiscounter.create(0.2, LocalTime.of(23, 0), LocalTime.of(1, 0)));

        OptionMenu optionMenu = OptionMenu.create("옵션1", Money.of(1000));
        OptionGroupMenu group = OptionGroupMenu.create("그룹1");
        group.updateOptionMenus(optionMenu);

        Menu menu1 = Menu.create("짜장면", Money.of(10000), discounter, category1.getId());
        Menu menu2 = Menu.create("짬뽕", Money.of(5000), discounter, category2.getId() );
        menu1.updateOptionGroups(group);
        menu2.updateOptionGroups(group);

        category1.updateMenus(menu1);
        category1.updateMenus(menu2);

        category2.updateMenus(menu1);

        List<CategoryInfo> lists = menuService.getAllMenusByStoreId(store.getId().toString());
//        System.out.println(lists.get(0).getMenus().get(0).getName());
//        System.out.println(lists.get(1).getMenus().get(0).getName());

        lists.get(0).getMenus().forEach(menuInfo -> {
            System.out.println(menuInfo.getName());
            System.out.printf("원래 가격: %d\n", menuInfo.getOriginalPrice());
            System.out.printf("할인 가격: %d\n", menuInfo.getDiscountPrice().get());
        });

    }
}
