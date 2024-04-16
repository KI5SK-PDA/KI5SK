package kiosk.model.discounter;

import common.vo.Money;

public abstract class PercentageDiscounter implements Discounter {
    private final double discountRate;

    PercentageDiscounter(double discountRate) {
        this.discountRate = discountRate;
    }

    public Money getDiscountPrice(Money price) {
        return price.subtract(price.applyRate(discountRate));
    }

    public String getDiscountInfo() {
        return String.format("%.1f%% 할인되었습니다.", discountRate * 100);
    }
}