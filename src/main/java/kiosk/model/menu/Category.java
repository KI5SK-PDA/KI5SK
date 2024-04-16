package kiosk.model.menu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Category {
    @Setter(AccessLevel.PACKAGE)
    private CategoryId id;
    private String name;
    private List<Menu> menus;

    private Category(String name) {
        this.name = name;
        this.menus = new ArrayList<>();
    }

    public static Category create(String name) {
        return new Category(name);
    }

    public void updateMenus(Menu menu){
        menus.add(menu);
    }

    public List<Menu> getMenus(){
        return menus;
    }
}
