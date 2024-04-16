package kiosk.model.discounter;

import common.vo.Money;

import java.time.LocalTime;

public class AlwaysCashDiscounter extends CashDiscounter {

    AlwaysCashDiscounter(Money discountAmount) {
        super(discountAmount);
    }

    public static AlwaysCashDiscounter create(Money discountAmount){
        return new AlwaysCashDiscounter(discountAmount);
    }

    public Money getDiscountPrice(Money price) {
        return super.getDiscountPrice(price);
    }

    public String getDiscountInfo() {
        return super.getDiscountInfo();
    }
}
