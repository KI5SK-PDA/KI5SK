package view.menu;

import kiosk.service.menu.dto.res.MenuInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuGridPanel extends JPanel {
    public MenuGridPanel(List<MenuInfo> menuInfos) {
        this.setLayout(new GridLayout(0, 3));

        for (MenuInfo menuInfo : menuInfos) {
            add(new MenuPanel(menuInfo));
        }
    }
}
