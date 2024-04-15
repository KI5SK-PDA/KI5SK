package kiosk.model.store;

import common.vo.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Store {
    @Setter
    private StoreId id;
    private String name;
    private Money balance;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Store(").append(id).append(")\n");
        builder.append("Name: ").append(name).append("\n");
        builder.append("Balance: ").append(balance).append("\n");
        return builder.toString();
    }

}
