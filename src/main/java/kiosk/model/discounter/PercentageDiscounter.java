package kiosk.model.discounter;

import common.vo.Money;

public abstract class PercentageDiscounter implements Discounter {
    private double discountRate;

    public Money getDiscountPrice(Money price) {
        return null;
    }

    public String getDiscountInfo() {
        return null;
    }
}