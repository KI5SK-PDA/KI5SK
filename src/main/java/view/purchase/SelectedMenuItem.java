package view.purchase;

import shoppingbasket.service.dto.res.SelectedMenuResponse;

import javax.swing.*;
import java.awt.*;

public class SelectedMenuItem extends JButton {

    public SelectedMenuItem(SelectedMenuResponse selectedMenu){
        System.out.println(selectedMenu.getName());
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(450, 60));

        JLabel laMenuName = new JLabel(selectedMenu.getName());
        laMenuName.setFont(new Font("Arial", Font.BOLD, 18));
        add(laMenuName, BorderLayout.WEST);

        JLabel laMenuQuantity = new JLabel(selectedMenu.getQuantity()+"개");
        laMenuQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        add(laMenuQuantity, BorderLayout.EAST);

        String price = "";

        if (selectedMenu.getDiscountPrice().isEmpty()) {
            price = selectedMenu.getOriginalPrice()+"원";
        } else {
            price = "SALE "+selectedMenu.getDiscountPrice().get()+"원";
        }

        JLabel laMenuPrice = new JLabel(price);
        laMenuPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        laMenuPrice.setHorizontalAlignment(JLabel.RIGHT);
        add(laMenuPrice, BorderLayout.SOUTH);
    }
}
