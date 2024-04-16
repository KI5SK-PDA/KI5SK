package kiosk.model.menu;

import common.vo.Money;
import kiosk.model.discounter.Discounter;
import kiosk.model.store.StoreId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@DisplayName("카테고리 레포지토리 테스트")
public class CategoryRepositoryTest {
    private final CategoryRepository categoryRepository = CategoryRepository.getInstance();

    @AfterEach
    void clearData() {
        categoryRepository.clear();
    }

    @DisplayName("카테고리 레포지토리 저장 및 조회 테스트")
    @Test
    void saveAndRead(){
        Optional<Discounter> discounter = Optional.empty(); // 디스카운터 생성
        OptionMenu optionMenu = OptionMenu.create("옵션1", Money.of(1000)); // 옵션 생성
        OptionGroupMenu group = OptionGroupMenu.create("그룹1"); //옵션 카테고리 생성
        group.updateOptionMenus(optionMenu); // 옵션 카테고리에 옵션 넣음

        StoreId storeId = StoreId.of(1);

        Category category = Category.create("카테고리1");
        categoryRepository.create(category, storeId);
        System.out.println(category.getId()); // 001-00

        Menu menu = Menu.create("마라탕", Money.of(10000), discounter, category.getId());
        category.updateMenus(menu);

        System.out.println(category.getMenus().get(0).getName());//마라탕


    }
}
