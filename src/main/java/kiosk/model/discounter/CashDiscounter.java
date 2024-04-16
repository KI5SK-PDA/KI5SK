package kiosk.model.discounter;

import common.vo.Money;

public abstract class CashDiscounter implements Discounter {
    private Money discountCash;

    public Money getDiscountPrice(Money price) {
        return null;
    }

    public String getDiscountInfo() {
        return null;
    }
}