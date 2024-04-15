package kiosk.model.discounter;

import common.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("상시 현금 할인 테스트")
class AlwaysCashDiscounterTest {
    @DisplayName("할인 가격 확인")
    @Test
    void generateDiscounterTest(){
        Money discountAmount = Money.of(3000); // 3000원
        AlwaysCashDiscounter discounter = new AlwaysCashDiscounter(discountAmount);

        Money originalPrice = Money.of(10000); // 10000원
        Money discountedPrice = discounter.getDiscountPrice(originalPrice);

        System.out.println("할인 후 가격: " + discountedPrice);
        System.out.println(discounter.getDiscountInfo());
    }
}