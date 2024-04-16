package view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryTapButton extends JPanel {
    public CategoryTapButton(String name, ActionListener actionListener) {
        JButton button = new JButton(name);
        button.addActionListener(actionListener);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(100, 50));
        add(button);
//        addActionListener(actionListener);
//        setFont(new Font("Arial", Font.BOLD, 18));
//        setPreferredSize(new Dimension(400, 400));
    }
}
