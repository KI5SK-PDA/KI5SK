package view.menu;

import kiosk.service.menu.dto.res.MenuInfo;

import javax.swing.*;

public class MenuPanel extends JPanel {
    public MenuPanel(MenuInfo menuInfo) {
        JLabel jlabel = new JLabel(menuInfo.getName());

        add(jlabel);
    }
}