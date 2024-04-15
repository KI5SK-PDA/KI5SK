package kiosk.model.discounter;

import common.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

@DisplayName("시간 비율 할인 테스트")
class TimePercentageDiscounterTest {
    @DisplayName("할인 가격 확인")
    @Test
    void generateDiscounterTest(){
        double discountRate = 0.2; // 20%
        TimePercentageDiscounter perDiscounter = new TimePercentageDiscounter(discountRate, LocalTime.of(18, 0, 0), LocalTime.of(19, 0, 0));

        Money originalPrice = Money.of(10000);
        Money discountedPrice = perDiscounter.getDiscountPrice(originalPrice);

        System.out.println("할인 후 가격: " + discountedPrice);
        System.out.println(perDiscounter.getDiscountInfo());
    }
}