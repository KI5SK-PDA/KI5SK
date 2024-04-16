package kiosk.service.menu;

import common.vo.Money;
import kiosk.model.menu.*;
import kiosk.model.store.Store;
import kiosk.model.store.StoreRepository;
import kiosk.service.menu.dto.req.InsertMenuRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("MenuService 테스트")
public class MenuServiceInsertTest {
    private final StoreRepository storeRepository = StoreRepository.getInstance();
    private final CategoryRepository categoryRepository = CategoryRepository.getInstance();
    private final MenuService menuService = MenuService.newInstance();

    @DisplayName("카테고리 생성 테스트")
    @Test
    void testInsertCategory(){
        Store store = Store.create("가게1", Money.of(100000), "image");
        storeRepository.save(store);

        String categoryId1 = menuService.insertCategory(store.getId().toString(), "카테고리1");
        String categoryId2 = menuService.insertCategory(store.getId().toString(), "카테고리2");
        System.out.println(categoryId1);
        System.out.println(categoryId2);

    }

    @DisplayName("메뉴 생성 테스트")
    @Test
    void testInsertMenu(){
        Store store = Store.create("가게1", Money.of(100000), "image");
        storeRepository.save(store);
        String categoryId1 = menuService.insertCategory(store.getId().toString(), "카테고리1");
        InsertMenuRequest insertMenuRequest = InsertMenuRequest.builder().name("짜장면").originalPrice(10000).build();

        //할인 없는 경우
        String noDiscountMenuId = menuService.insertNoDiscountMenu(categoryId1, insertMenuRequest);
        System.out.println(noDiscountMenuId);
        categoryRepository.findById(CategoryId.of(categoryId1)).get().getMenus()
                .forEach(menu -> {
                    if(menu.getId().toString() == noDiscountMenuId){
                        System.out.println(menu.getName());
                        System.out.println(menu.getDiscounter());
                        System.out.println(menu.getDiscountPrice());
                    }
                });

        //시간 비율 할인 있는 경우
        String timePercentageMenuId = menuService.insertTimePercentageDiscountMenu(categoryId1, insertMenuRequest);
        System.out.println(timePercentageMenuId);
        categoryRepository.findById(CategoryId.of(categoryId1)).get().getMenus()
                .forEach(menu -> {
                    if(menu.getId().toString() == timePercentageMenuId){
                        System.out.println(menu.getName());
                        System.out.println(menu.getDiscounter());
                        System.out.println(menu.getDiscountPrice());
                    }
                });

        String alwaysCashMenuId = menuService.insertAlwaysCashDiscountMenu(categoryId1, insertMenuRequest);
        System.out.println(alwaysCashMenuId);
        categoryRepository.findById(CategoryId.of(categoryId1)).get().getMenus()
                .forEach(menu -> {
                    if(menu.getId().toString() == alwaysCashMenuId){
                        System.out.println(menu.getName());
                        System.out.println(menu.getDiscounter());
                        System.out.println(menu.getDiscountPrice());
                    }
                });

    }

    @DisplayName("옵션 생성 테스트")
    @Test
    void testInsertOption(){
        Store store = Store.create("가게1", Money.of(100000), "image");
        storeRepository.save(store);
        String categoryId1 = menuService.insertCategory(store.getId().toString(), "카테고리1");
        InsertMenuRequest insertMenuRequest = InsertMenuRequest.builder().name("짜장면").originalPrice(10000).build();
        String noDiscountMenuId = menuService.insertNoDiscountMenu(categoryId1, insertMenuRequest);

        //옵션 그룹
        String optionGroupId =menuService.insertOptionGroup(noDiscountMenuId, "옵션그룹1");
        categoryRepository.findById(CategoryId.of(categoryId1)).get().getMenus()
                .forEach(menu -> {
                    if(menu.getId().toString() == noDiscountMenuId){
                        System.out.println(menu.getAllOptionMenus().get(0).getName());
                    }
                });

        //옵션 메뉴들
        menuService.insertOptionMenu(noDiscountMenuId, optionGroupId, "옵션메뉴1", 2000);
        categoryRepository.findById(CategoryId.of(categoryId1)).get().getMenus()
                .forEach(menu -> {
                    if(menu.getId().toString() == noDiscountMenuId){
                        System.out.println(menu.getAllOptionMenus().get(0).getName());
                        menu.getAllOptionMenus().forEach(optionGroupMenu -> {
                            if(optionGroupId.toString() == optionGroupMenu.getId()){
                                System.out.println(optionGroupMenu.getOptionMenus().get(0).getName());
                            }
                        });
                    }
                });
    }

}
