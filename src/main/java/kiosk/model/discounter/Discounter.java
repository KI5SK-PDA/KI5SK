package kiosk.model.discounter;

import common.vo.Money;

public interface Discounter {
    Money getDiscountPrice(Money price);
    String getDiscountInfo();
}