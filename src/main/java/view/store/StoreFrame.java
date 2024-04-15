package view.store;

import javax.swing.*;

public class StoreFrame extends JFrame {
    public StoreFrame() {
        setTitle("스토어 리스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(new StoreGridPanel());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll Speed
        add(scrollPane);

        setSize(800, 600);
        setVisible(true);
    }
}
