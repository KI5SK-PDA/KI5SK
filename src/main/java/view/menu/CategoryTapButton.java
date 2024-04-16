package view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryTapButton extends JButton {
    public CategoryTapButton(String name, ActionListener actionListener) {
        super(name);
        addActionListener(actionListener);
        setFont(new Font("Arial", Font.BOLD, 18));
    }
}
