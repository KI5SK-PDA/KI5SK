package view.controller;

import card.controller.CardController;
import shoppingbasket.controller.ShoppingBasketController;
import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;
import view.card.CardFrame;
import view.menu.MenuFrame;
import view.select.SelectFrame;
import view.store.StoreFrame;
import view.store.StoreGridPanel;
import view.user.PopUpFrame;
import view.user.UserFrame;

import javax.swing.*;

public class MainViewController {
    private JFrame selectFrame;
    private JFrame storeFrame;
    private JFrame cardFrame;
    private JFrame menuFrame;
    private JFrame userFrame;

    private MainViewController () {
        userFrame = new UserFrame(
                        new PopUpFrame(),
                        new SignUpController(),
                        new LoginController(),
                        new LogoutController(),
                        new UserFrameToSelectFrameTransition());
//        selectFrame = new SelectFrame(
//                new SelectFrameToStoreTransition(),
//                new SelectFrameToCardTransition(),
//                new SelectFrameToUserFrameTransition()
//        );
        //        this.storeFrame = new StoreFrame(new StoreGridPanel(new StoreToMenuTransition()));
    }

    public static MainViewController getInstance () {
        return new MainViewController();
    }

    public void run() {
//        selectFrame.setVisible(true);
        userFrame.setVisible(true);
    }

    private class UserFrameToSelectFrameTransition implements BasicTransition {
        @Override
        public void switchScreen(){
            userFrame.dispose();
            selectFrame = new SelectFrame(
                    new SelectFrameToStoreTransition(),
                    new SelectFrameToCardTransition(),
                    new SelectFrameToUserFrameTransition()
            );
            selectFrame.setVisible(true);
        }
    }

    private class CardFrameToSelectFrameTransition implements  BasicTransition{
        @Override
        public void switchScreen() {
            cardFrame.dispose();
            selectFrame = new SelectFrame(
                    new SelectFrameToStoreTransition(),
                    new SelectFrameToCardTransition(),
                    new SelectFrameToUserFrameTransition()
            );
            selectFrame.setVisible(true);
        }
    }

    private class SelectFrameToStoreTransition implements BasicTransition {
        @Override
        public void switchScreen() {
            selectFrame.dispose();
            storeFrame = new StoreFrame(new StoreGridPanel(new StoreToMenuTransition()), new StoreToSelectFrameTransition());
            storeFrame.setVisible(true);
        }
    }

    private class SelectFrameToCardTransition implements BasicTransition{
        @Override
        public void switchScreen() {
            selectFrame.dispose();
            cardFrame = new CardFrame(new CardController(), new CardFrameToSelectFrameTransition());
            cardFrame.setVisible(true);
        }
    }

    private class StoreToMenuTransition implements StoreToMenu {
        @Override
        public void switchMenu(String storeId) {
            storeFrame.dispose();
            menuFrame = new MenuFrame(storeId, new MenuToStoreFrameTransition(), new ShoppingBasketController());
        }
    }

    private class SelectFrameToUserFrameTransition implements BasicTransition{
        @Override
        public void switchScreen() {
            selectFrame.dispose();
            userFrame = new UserFrame(
                    new PopUpFrame(),
                    new SignUpController(),
                    new LoginController(),
                    new LogoutController(),
                    new UserFrameToSelectFrameTransition()
            );
            userFrame.setVisible(true);
        }
    }

    private class StoreToSelectFrameTransition implements BasicTransition {
        @Override
        public void switchScreen() {
            storeFrame.dispose();
            selectFrame = new SelectFrame(
                new SelectFrameToStoreTransition(),
                new SelectFrameToCardTransition(),
                new SelectFrameToUserFrameTransition()
            );
            selectFrame.setVisible(true);
        }
    }

    private class MenuToStoreFrameTransition implements BasicTransition {
        @Override
        public void switchScreen() {
            menuFrame.dispose();
            storeFrame = new StoreFrame(new StoreGridPanel(new StoreToMenuTransition()), new StoreToSelectFrameTransition());
            storeFrame.setVisible(true);
        }
    }
}
