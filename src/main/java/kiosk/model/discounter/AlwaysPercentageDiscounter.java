package kiosk.model.discounter;

import common.vo.Money;

public class AlwaysPercentageDiscounter extends PercentageDiscounter {

    AlwaysPercentageDiscounter(double discountRate) {
        super(discountRate);
    }

    public Money getDiscountPrice(Money price) {
        return super.getDiscountPrice(price);
    }

    public String getDiscountInfo() {
        return super.getDiscountInfo();
    }
}
