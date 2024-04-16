package view.menu;

import kiosk.controller.KioskController;
import kiosk.service.menu.GetMenuService;
import kiosk.service.store.CommonStoreService;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private final GetMenuService menuService = KioskController.newInstance();
    private final CommonStoreService storeService = KioskController.newInstance();

    public MenuFrame(String storeId) {
        setTitle("메뉴");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1, 20,20));

        JLabel header = new JLabel(storeService.getStoreById(storeId).getName());
        header.setFont(new Font("Arial", Font.BOLD, 40));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header);

        setSize(800, 700);
        setVisible(true);
    }
}
