package kiosk.model.discounter;

import common.vo.Money;

public class AlwaysCashDiscounter extends CashDiscounter {

    AlwaysCashDiscounter(Money discountAmount) {
        super(discountAmount);
    }

    public Money getDiscountPrice(Money price) {
        return super.getDiscountPrice(price);
    }

    public String getDiscountInfo() {
        return super.getDiscountInfo();
    }
}
