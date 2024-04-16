package view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryTapButton extends JPanel {
    public CategoryTapButton(JButton button, ActionListener actionListener) {
        button.addActionListener(actionListener);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(100, 50));
        add(button);
    }
}
