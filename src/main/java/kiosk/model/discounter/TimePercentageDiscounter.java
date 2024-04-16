package kiosk.model.discounter;

import common.vo.Money;
import kiosk.model.menu.CategoryId;
import kiosk.model.menu.Menu;

import java.time.LocalTime;
import java.util.Optional;

public class TimePercentageDiscounter extends PercentageDiscounter {
    private final LocalTime startTime;
    private final LocalTime endTime;

    public TimePercentageDiscounter (double discountRate, LocalTime startTime, LocalTime endTime) {
        super(discountRate);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimePercentageDiscounter create(double discountRate, LocalTime startTime, LocalTime endTime){
        return new TimePercentageDiscounter(discountRate, startTime, endTime);
    }

    public Money getDiscountPrice(Money price) {
        if (isDiscountTime()) {
            return super.getDiscountPrice(price);
        } else {
            return price;
        }
    }

    public String getDiscountInfo() {
        if (isDiscountTime()) {
            return super.getDiscountInfo();
        } else {
            return "할인 시간이 아닙니다.";
        }
    }

    public boolean isDiscountTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
    }
}
