package kiosk.model.discounter;

import common.vo.Money;

import java.time.LocalTime;

public class TimePercentageDiscounter extends PercentageDiscounter {
    private final LocalTime startTime;
    private final LocalTime endTime;

    TimePercentageDiscounter(double discountRate, LocalTime startTime, LocalTime endTime) {
        super(discountRate);
        this.startTime = startTime;
        this.endTime = endTime;
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

    private boolean isDiscountTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
    }
}
