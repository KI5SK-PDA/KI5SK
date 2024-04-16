package kiosk.service.menu;

import common.vo.Money;
import kiosk.model.discounter.TimePercentageDiscounter;
import kiosk.model.menu.Category;
import kiosk.model.menu.CategoryId;
import kiosk.model.menu.CategoryRepository;
import kiosk.model.menu.Menu;
import kiosk.model.store.Store;
import kiosk.model.store.StoreId;
import kiosk.model.store.StoreRepository;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.menu.dto.res.MenuInfo;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuService implements GetMenuService, InsertMenuService {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private MenuService() {
        this.storeRepository = StoreRepository.getInstance();
        this.categoryRepository = CategoryRepository.getInstance();
    }

    public static MenuService newInstance() {
        return new MenuService();
    }

    @Override
    public List<CategoryInfo> getAllMenusByStoreId(String storeId) { //스토어 Id 값으로 카테고리Info가져오기

        Store store = storeRepository.findById(StoreId.of(storeId)).orElseThrow(//스토어 가져옴
            () -> new RuntimeException("Store not found"));

        List<CategoryInfo> categoryInfos = store.getCategoryIds().stream().map(categoryID->{ //store로 부터 카테고리 ID값들 가져옴
            Category category = categoryRepository.findById(categoryID).orElseThrow( // 각 카테고리 ID를 돌면서 카테고리 객체 가져옴
                    () -> new RuntimeException("Category not found"));

            List<MenuInfo> menuInfos = category.getMenus().stream()//가져온 카테고리 객체로 getMenus로 메뉴리스트 가져와서 MenuInfo List로 만들어줌
                    .map(MenuInfo::from)
                    .collect(Collectors.toList());

            return CategoryInfo.builder() //카테고리 Info만듬
                    .name(category.getName())
                    .menus(menuInfos)
                    .build();

        }).collect(Collectors.toList());

        return categoryInfos;
    }

    @Override
    public void insertCategory(String storeId, String name) {

    }

    @Override
    public void insertBreakfastDiscountMenu(String categoryId, String name, int price) {
        Menu.create(
                name,
                Money.of(price),
                Optional.of(
                        new TimePercentageDiscounter(20, LocalTime.of(12,12), LocalTime.of(10,10))),
                CategoryId.of(categoryId));
    }

    @Override
    public void insertEventDiscountMenu(String categoryId, String name, int price) {

    }
}
