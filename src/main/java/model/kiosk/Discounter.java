package model.kiosk;

import model.vo.Money;

public interface Discounter {
    Money getDiscountPrice(Money price);
    String getDiscountInfo();
}

class CashDiscounter implements Discounter {
//    private 필드
    
    public Money getDiscountPrice(Money price) {
        return null;
    }

    public String getDiscountInfo() {
        return null;
    }
}