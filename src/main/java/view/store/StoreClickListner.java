package view.store;

import view.menu.MenuFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StoreClickListner implements MouseListener {
    private final String storeId;

    public StoreClickListner(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ((JComponent) e.getSource()).setVisible(false);
        System.out.println(storeId);
        new MenuFrame(storeId).setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
