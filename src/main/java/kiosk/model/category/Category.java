package kiosk.model.category;

import kiosk.model.menu.Menu;

import java.util.List;

public class Category {

    private int offsetId;
    private String name;
    private List<Menu> menus;

    private void updateMenus(Menu menu){
        menus.add(menu);
    }

    public List<Menu> getMenus(){
        return menus;
    }
}
