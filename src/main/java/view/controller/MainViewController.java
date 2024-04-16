package view.controller;

import view.menu.MenuFrame;
import view.store.StoreFrame;
import view.store.StoreGridPanel;

import javax.swing.*;

public class MainViewController {
    private JFrame storeFrame;
    private JFrame menuFrame;

    private MainViewController () {
        this.storeFrame = new StoreFrame(new StoreGridPanel(new StoreToMenuTransition()));
    }

    public static MainViewController getInstance () {
        return new MainViewController();
    }

    public void run() {
        storeFrame.setVisible(true);
    }

    private class StoreToMenuTransition implements StoreToMenu {
        @Override
        public void switchMenu(String storeId) {
            storeFrame.dispose();
            menuFrame = new MenuFrame(storeId);
            menuFrame.setVisible(true);
        }
    }
}
