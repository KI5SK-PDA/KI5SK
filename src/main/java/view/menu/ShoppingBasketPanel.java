package view.menu;

import javax.swing.*;
import java.awt.*;

public class ShoppingBasketPanel extends JPanel {
    public ShoppingBasketPanel() {
        Box mainBox = Box.createVerticalBox();

        Box basketScroll = Box.createVerticalBox();
        Box basketItemList = Box.createVerticalBox();
        for (int i=0;i<40;i++) {
            JLabel label = new JLabel("메뉴 " + i);
            label.setFont(new Font("Serif", Font.BOLD, 20));
            basketItemList.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(basketItemList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        basketScroll.setPreferredSize(new Dimension(500, 600));
        basketScroll.add(scrollPane);
        mainBox.add(basketScroll);

        JPanel purchasePanel = new JPanel();
        JButton purchaseButton = new JButton("결제");
        purchaseButton.setFont(new Font("Arial", Font.BOLD, 30));
        purchaseButton.setPreferredSize(new Dimension(300, 40));
        purchasePanel.add(purchaseButton);
        mainBox.add(purchasePanel);

        add(mainBox);
    }
}
