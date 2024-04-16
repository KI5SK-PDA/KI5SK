package view.store;

import view.controller.BasicTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreFrame extends JFrame {
    public StoreFrame(JPanel storeGridPanel, BasicTransition transition) {
        setTitle("스토어 리스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box mainBox = Box.createVerticalBox();

        JButton backButton = new JButton("뒤로가기");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setPreferredSize(new Dimension(100, 50));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transition.switchScreen();
            }
        });

        mainBox.add(backButton);
        JScrollPane scrollPane = new JScrollPane(storeGridPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll Speed
        mainBox.add(scrollPane);

        add(mainBox);


        setSize(800, 600);
        setVisible(true);
    }
}
