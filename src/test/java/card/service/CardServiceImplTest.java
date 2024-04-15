package card.service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("카드 서비스 테스트")
class CardServiceImplTest {

    CardServiceImpl cardService = new CardServiceImpl();

    @DisplayName("새로운 카드번호 발급")
    @Test
    void newCardNo() {
        System.out.println(cardService.newCardNo());
    }
}