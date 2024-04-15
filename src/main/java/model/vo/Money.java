package model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Money {
    private int amount;

    public static Money of(int amount) {
        if (amount < 0) throw new IllegalArgumentException("돈은 0 이하로 설정될 수 없음");
        return new Money(amount);
    }

    public int toInt() {
        return this.amount;
    }

    public Money add(Money money) {
        return new Money(this.amount + money.amount);
    }

    public Money subtract(Money money) {
        return new Money(this.amount - money.amount);
    }
}
