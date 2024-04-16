package view.store;

import view.controller.StoreToMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StoreClickListner implements MouseListener {
    private final String storeId;
    private final StoreToMenu storeToMenu;

    public StoreClickListner(String storeId, StoreToMenu storeToMenu) {
        this.storeId = storeId;
        this.storeToMenu = storeToMenu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        storeToMenu.switchMenu(storeId);
        System.out.println(storeId+"");
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
