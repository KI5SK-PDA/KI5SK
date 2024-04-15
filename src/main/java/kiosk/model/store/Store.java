package kiosk.model.store;

import common.vo.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Store {
    @Setter
    private StoreId id;
    private String name;
    private Money balance;
    private String imagePath;
    private List<String> menuIds; // TODO: Menu ID로 바꾸기

    private Store(String name, Money balance, String imagePath) {
        this.name = name;
        this.balance = balance;
        this.imagePath = imagePath;
        this.menuIds = new ArrayList<>();
    }

    public static Store create(String name, Money balance,String imagePath) {
        return new Store(name, balance, imagePath);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Store(").append(id).append(")\n");
        builder.append("Name: ").append(name).append("\n");
        builder.append("Balance: ").append(balance).append("\n");
        return builder.toString();
    }

}
