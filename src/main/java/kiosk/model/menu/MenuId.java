package kiosk.model.menu;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuId {
    private String id;

    public static kiosk.model.menu.MenuId of(CategoryId categoryId,int id) {
        if (id < 0 || id > 99999)
            throw new IllegalArgumentException("MenuID must be between 0 and 99");

        return new kiosk.model.menu.MenuId(String.format("%s-%05d", categoryId.toString(),id));
    }

    @Override
    public String toString() {
        return id;
    }
}

