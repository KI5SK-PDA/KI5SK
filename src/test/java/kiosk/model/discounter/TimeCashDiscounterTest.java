package kiosk.model.discounter;

import common.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

@DisplayName("시간 현금 할인 테스트")
class TimeCashDiscounterTest {
    @DisplayName("생성 테스트")
    @Test
    void generateDiscounterTest(){
        Money discountAmount = Money.of(500); // Sets the discount amount to 500 won
        TimeCashDiscounter cashDiscounter = new TimeCashDiscounter(discountAmount, LocalTime.of(16, 0, 0), LocalTime.of(17, 0, 0));

        Money originalPrice = Money.of(1000); // Example price
        Money discountedPrice = cashDiscounter.getDiscountPrice(originalPrice);

        System.out.println("할인 후 가격: " + discountedPrice);
        System.out.println(cashDiscounter.getDiscountInfo());

    }
}