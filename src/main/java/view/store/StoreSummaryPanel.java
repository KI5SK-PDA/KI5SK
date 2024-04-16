package view.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class StoreSummaryPanel extends JPanel {

    public StoreSummaryPanel(String id, String name, String imagePath, MouseListener storeClickListener) {
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());

        setPreferredSize(new Dimension(300, 300));
        JLabel imageLabel = new StoreImage(new ImageIcon(getClass().getClassLoader().getResource(imagePath)));
        imageLabel.addMouseListener(storeClickListener);
        box.add(imageLabel);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.addMouseListener(storeClickListener);
        box.add(nameLabel);

        add(box);
    }

    private static class StoreImage extends JLabel {
        StoreImage(ImageIcon imageIcon) {
            this.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        }

        @Override
        public void paintComponent(Graphics g) {
            RoundRectangle2D r = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
            g.setClip(r);

            setOpaque(false);
            super.paintComponent(g);
        }
    }
}
