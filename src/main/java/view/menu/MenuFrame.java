package view.menu;

import javax.swing.*;

public class MenuFrame extends JFrame {
    public MenuFrame(String storeId) {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel(storeId));
        setSize(800, 700);
    }
}
