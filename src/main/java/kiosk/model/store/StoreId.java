package kiosk.model.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreId {
    private String id;

    public static StoreId of(int id) {
        if (id < 0 || id > 999)
            throw new IllegalArgumentException("StoreID must be between 0 and 999");

        return new StoreId(String.format("%03d", id));
    }

    @Override
    public String toString() {
        return id;
    }
}
