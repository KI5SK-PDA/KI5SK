package kiosk.service.menu;

import common.vo.Money;
import kiosk.model.discounter.AlwaysCashDiscounter;
import kiosk.model.discounter.Discounter;
import kiosk.model.discounter.TimePercentageDiscounter;
import kiosk.model.menu.Category;
import kiosk.model.menu.CategoryId;
import kiosk.model.menu.CategoryRepository;
import kiosk.model.menu.Menu;
import kiosk.model.menu.OptionGroupMenu;
import kiosk.model.menu.OptionMenu;
import kiosk.model.store.Store;
import kiosk.model.store.StoreId;
import kiosk.model.store.StoreRepository;
import kiosk.service.menu.dto.req.InsertMenuRequest;
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
    public String insertCategory(String storeId, String name) {
        Store store = storeRepository.findById(StoreId.of(storeId)).orElseThrow(() -> new RuntimeException("Store not found"));

        Category category = Category.create(name);
        Category newCategory = categoryRepository.create(category, StoreId.of(storeId));

        store.getCategoryIds().add(newCategory.getId());
        return newCategory.getId().toString();
    }

    @Override
    public String insertNoDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest) {
        Category category = categoryRepository.findById(CategoryId.of(categoryId)).orElseThrow( // 각 카테고리 ID를 돌면서 카테고리 객체 가져옴
                () -> new RuntimeException("Category not found"));

        Money originalPrice = Money.of(insertMenuRequest.getOriginalPrice());
        Menu menu = Menu.create(insertMenuRequest.getName(), originalPrice, Optional.empty(), CategoryId.of(categoryId));

        category.updateMenus(menu);
        return menu.getId().toString();
    }

    //시간비율할인 메뉴 생성
    @Override
    public String insertTimePercentageDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest) {
        Category category = categoryRepository.findById(CategoryId.of(categoryId)).orElseThrow( // 각 카테고리 ID를 돌면서 카테고리 객체 가져옴
                () -> new RuntimeException("Category not found"));


        Money originalPrice = Money.of(insertMenuRequest.getOriginalPrice());
        Discounter discounter = TimePercentageDiscounter.create(0.2, LocalTime.of(8, 0), LocalTime.of(11, 0));// 일단 고정
        Menu menu = Menu.create(insertMenuRequest.getName(), originalPrice, Optional.of(discounter), CategoryId.of(categoryId));

        category.updateMenus(menu);
        return menu.getId().toString();
    }


    //항시 현금할인 생성
    @Override
    public String insertAlwaysCashDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest) {
        Category category = categoryRepository.findById(CategoryId.of(categoryId)).orElseThrow( // 각 카테고리 ID를 돌면서 카테고리 객체 가져옴
                () -> new RuntimeException("Category not found"));


        Money originalPrice = Money.of(insertMenuRequest.getOriginalPrice());
        Discounter discounter = AlwaysCashDiscounter.create(Money.of(1000));// 일단 고정
        Menu menu = Menu.create(insertMenuRequest.getName(), originalPrice, Optional.of(discounter), CategoryId.of(categoryId));

        category.updateMenus(menu);
        return menu.getId().toString();
    }

    @Override
    public String insertOptionGroup(String menuId, String name) {
        Category category = categoryRepository.findById(CategoryId.of(toCategoryIdFrom(menuId))).orElseThrow(
                () -> new RuntimeException("Category not found"));
        OptionGroupMenu optionGroupMenu = OptionGroupMenu.create(name);
        category.getMenus().forEach(menu -> {
            if(menu.getId().toString() == menuId){
                menu.updateOptionGroups(optionGroupMenu);
            }
        });

        return optionGroupMenu.getId();
    }

    @Override
    public String insertOptionMenu(String menuId, String optionGroupId, String name, int price) {
        Category category = categoryRepository.findById(CategoryId.of(toCategoryIdFrom(menuId))).orElseThrow(
                () -> new RuntimeException("Category not found"));

        OptionMenu optionMenu = OptionMenu.create(name, Money.of(price));

        category.getMenus().forEach(menu -> {
            if(menu.getId().toString() == menuId){
                menu.getAllOptionMenus().forEach(optionGroupMenu -> {
                    if(optionGroupId.toString() == optionGroupMenu.getId()){
                        optionGroupMenu.updateOptionMenus(optionMenu);
                    }
                });
            }
        });

        return optionMenu.getId().toString();

    }

    private String toCategoryIdFrom(String menuId) {
        return menuId.substring(0, 6); //0번부터 5번까지
    }
}
