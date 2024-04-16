package kiosk.model.menu;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class OptionGroupMenu {

    private final String id = UUID.randomUUID().toString();// id값이 있어야 하나?
    private String name;
    private List<OptionMenu> optionMenus;

    public OptionGroupMenu(String name){
        this.name = name;
        this.optionMenus = new ArrayList<>();
    }
    public void updateOptionMenus(OptionMenu optionMenu){
        optionMenus.add(optionMenu);
    }

    public List<OptionMenu> getAllOptionMenus(){
        return optionMenus;
    }
}
