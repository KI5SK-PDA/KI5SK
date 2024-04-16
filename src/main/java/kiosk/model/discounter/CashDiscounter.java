package kiosk.model.discounter;

import common.vo.Money;

public abstract class CashDiscounter implements Discounter {
    private final Money discountAmount;

    CashDiscounter(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money getDiscountPrice(Money price) {
        return price.subtract(discountAmount);
    }

    public String getDiscountInfo() {
        return String.format("%,d 원 할인되었습니다", discountAmount.toInt());
    }
}