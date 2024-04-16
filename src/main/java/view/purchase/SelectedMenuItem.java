package view.purchase;

import shoppingbasket.service.dto.res.SelectedMenuResponse;
import shoppingbasket.service.dto.res.SelectedOptionResponse;

import javax.swing.*;
import java.awt.*;

public class SelectedMenuItem extends JButton {

    SelectedMenuResponse selectedMenu;

    public SelectedMenuItem(SelectedMenuResponse selectedMenu){
        this.selectedMenu = selectedMenu;
        setLayout(new BorderLayout());

        int height = 0;

        JPanel topPane = new JPanel(new BorderLayout());
        topPane.setBackground(Color.white);

        JLabel laMenuName = new JLabel(selectedMenu.getName());
        laMenuName.setFont(new Font("Arial", Font.BOLD, 18));
        topPane.add(laMenuName, BorderLayout.WEST);

        JLabel laMenuQuantity = new JLabel(selectedMenu.getQuantity()+"개");
        laMenuQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        topPane.add(laMenuQuantity, BorderLayout.EAST);

        add(topPane, BorderLayout.NORTH);

        JPanel optionPane = new JPanel(new FlowLayout());
        optionPane.setBackground(Color.white);
        if(selectedMenu.getSelectedOptions() != null){
            for(SelectedOptionResponse selectedOption : selectedMenu.getSelectedOptions()){
                JPanel middlePane = new JPanel(new BorderLayout());
                middlePane.setBackground(Color.white);
                middlePane.setPreferredSize(new Dimension(430, 20));

                JLabel optionName = new JLabel(selectedOption.getName());
                optionName.setFont(new Font("Arial", Font.PLAIN, 12));
                optionName.setForeground(Color.lightGray);
                middlePane.add(optionName, BorderLayout.WEST);

                JLabel optionPrice = new JLabel(selectedOption.getPrice()+"원");
                optionPrice.setFont(new Font("Arial", Font.PLAIN, 12));
                optionPrice.setForeground(Color.lightGray);
                middlePane.add(optionPrice, BorderLayout.EAST);

                height += 25;

                optionPane.add(middlePane);
            }
        }
        add(optionPane, BorderLayout.CENTER);

        String price = getPrice();
        JLabel laMenuPrice = new JLabel(price);
        laMenuPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        laMenuPrice.setHorizontalAlignment(JLabel.RIGHT);
        add(laMenuPrice, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(450, height+60));
    }

    public String getPrice(){
        if (selectedMenu.getDiscountPrice().isEmpty()) {
            return selectedMenu.getOriginalPrice()+"원";
        }

        return "SALE "+selectedMenu.getDiscountPrice().get()+"원";
    }
}
