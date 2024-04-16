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

        setPreferredSize(new Dimension(450, 60));

        JPanel topPane = new JPanel(new BorderLayout());
        topPane.setBackground(Color.white);

        JLabel laMenuName = new JLabel(selectedMenu.getName());
        laMenuName.setFont(new Font("Arial", Font.BOLD, 18));
        topPane.add(laMenuName, BorderLayout.WEST);

        JLabel laMenuQuantity = new JLabel(selectedMenu.getQuantity()+"개");
        laMenuQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        topPane.add(laMenuQuantity, BorderLayout.EAST);

        add(topPane, BorderLayout.NORTH);

        JPanel optionPane = new JPanel(new FlowLayout(FlowLayout.LEFT,2, 2));
        if(selectedMenu.getSelectedOptions() != null){
            for(SelectedOptionResponse selectedOption : selectedMenu.getSelectedOptions()){
                JLabel optionName = new JLabel(selectedOption.getName());
                optionName.setFont(new Font("Arial", Font.PLAIN, 12));
                optionName.setForeground(Color.lightGray);
                optionPane.add(optionName);
            }
        }
        add(optionPane, BorderLayout.CENTER);

        String price = getPrice();
        JLabel laMenuPrice = new JLabel(price);
        laMenuPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        laMenuPrice.setHorizontalAlignment(JLabel.RIGHT);
        add(laMenuPrice, BorderLayout.SOUTH);
    }

    public String getPrice(){
        if (selectedMenu.getDiscountPrice().isEmpty()) {
            return selectedMenu.getOriginalPrice()+"원";
        }
        return "SALE "+selectedMenu.getDiscountPrice().get()+"원";
    }
}
