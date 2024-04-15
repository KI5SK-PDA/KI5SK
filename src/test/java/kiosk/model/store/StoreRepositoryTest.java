package kiosk.model.store;


import common.vo.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("가게 레포지토리 테스트")
class StoreRepositoryTest {
    private final StoreRepository storeRepository = StoreRepository.getInstance();

    @AfterEach
    void clearData() {
        storeRepository.clear();
    }

    @DisplayName("가게 레포지토리 저장 및 조회")
    @Test
    void saveAndRead() {
        // given
        Store maraStore = Store.create("쿵후마라탕 성수점", Money.of(100), "image");
        Store bbq = Store.create("BBQ 성수점", Money.of(200), "image");

        // when
        storeRepository.save(maraStore);
        storeRepository.save(bbq);
        List<Store> stores = storeRepository.findAll();

        // then
        Assertions.assertThat(stores).hasSize(2);
    }
}