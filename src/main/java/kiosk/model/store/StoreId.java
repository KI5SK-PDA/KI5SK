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

    public static StoreId of(String id) {
        return new StoreId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
