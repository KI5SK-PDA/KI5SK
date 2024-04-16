package kiosk.model.menu;

import kiosk.model.store.StoreId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryId {
    private String id;

    public static CategoryId of(StoreId storeId, int id) {
        if (id < 0 || id > 99)
            throw new IllegalArgumentException("StoreID must be between 0 and 99");

        return new CategoryId(String.format("%s-%02d", storeId.toString(), id));
    }

    public static CategoryId of(String id) {
        return new CategoryId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
