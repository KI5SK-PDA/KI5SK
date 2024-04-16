package view.purchase;

import view.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class PurchaseDialog extends JDialog {

    JPanel menuPanel = new SelectedMenuPanel();
    JPanel purchasePanel = new PurchasePanel();

    public PurchaseDialog(JFrame frame, String title){
        super(frame, title);
        setLayout(new GridLayout(0,2,10,10));
        setSize(1000, 600);

        add(menuPanel);
        add(purchasePanel);

        setResizable(false);
    }
}
