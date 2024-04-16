package view.controller;

import card.controller.CardController;
import card.service.CardServiceImpl;
import view.card.CardFrame;
import view.menu.MenuFrame;
import view.select.SelectFrame;
import view.store.StoreFrame;
import view.store.StoreGridPanel;

import javax.swing.*;

public class MainViewController {
    private JFrame selectFrame;
    private JFrame storeFrame;
    private JFrame cardFrame;
    private JFrame menuFrame;

    private MainViewController () {
        selectFrame = new SelectFrame(new SelectFrameToStoreTransition(), new SelectFrameToCardTransition());
        //        this.storeFrame = new StoreFrame(new StoreGridPanel(new StoreToMenuTransition()));
    }

    public static MainViewController getInstance () {
        return new MainViewController();
    }

    public void run() {
        selectFrame.setVisible(true);
    }

    private class SelectFrameToStoreTransition implements BasicTransition {
        @Override
        public void switchScreen() {
            selectFrame.dispose();
            storeFrame = new StoreFrame(new StoreGridPanel(new StoreToMenuTransition()));
            storeFrame.setVisible(true);
        }
    }

    private class SelectFrameToCardTransition implements  BasicTransition{
        @Override
        public void switchScreen() {
            selectFrame.dispose();
            cardFrame = new CardFrame(new CardController());
            cardFrame.setVisible(true);
        }
    }

    private class StoreToMenuTransition implements StoreToMenu {
        @Override
        public void switchMenu(String storeId) {
            storeFrame.dispose();
            menuFrame = new MenuFrame(storeId);
        }
    }
}
