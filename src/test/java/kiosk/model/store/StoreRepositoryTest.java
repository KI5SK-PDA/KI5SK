package kiosk.model.store;


import common.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("가게 레포지토리 테스트")
class StoreRepositoryTest {
    @DisplayName("가게 레포지토리 저장 및 조회")
    @Test
    void saveAndRead() {
        StoreRepository repository = StoreRepository.getInstance();

        repository.save(Store.builder()
                .name("쿵후마라탕")
                .balance(Money.of(100))
            .build());

        repository.save(Store.builder()
                .name("맘스터치")
                .balance(Money.of(200))
            .build());

        for (Store store : repository.findAll())
            System.out.println(store.toString());

    }
}