package kiosk.model.discounter;

import common.vo.Money;

public abstract class PercentageDiscounter implements Discounter {
    private final double discountRate;

    PercentageDiscounter(double discountRate) {
        this.discountRate = discountRate;
    }

    public Money getDiscountPrice(Money price) {
        double discountAmount = price.toInt() * discountRate;
        int discountedPrice = price.toInt() - (int) Math.round(discountAmount);
        return Money.of(discountedPrice);
    }

    public String getDiscountInfo() {
        return String.format("%,2f%% 할인되었습니다.", discountRate * 100);
    }
}