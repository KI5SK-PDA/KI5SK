package kiosk.model.menu;

import common.vo.Money;
import lombok.Getter;

import java.util.UUID;

public class OptionMenu {
    @Getter
    private final String id = UUID.randomUUID().toString();
    private final String name;
    private final Money price;

    public OptionMenu(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
