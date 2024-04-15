package kiosk.model.menu;

import java.util.List;
import java.util.UUID;

public class OptionGroupMenu {

    private final String id = UUID.randomUUID().toString();// id값이 있어야 하나?
    private List<OptionMenu> optionMenus;

    public void updateOptionMenus(OptionMenu optionMenu){
        optionMenus.add(optionMenu);
    }

    public List<OptionMenu> getAllOptionMenus(){
        return optionMenus;
    }
}
