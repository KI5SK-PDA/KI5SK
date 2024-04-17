package view.purchase;

import shoppingbasket.controller.SelectedMenuObserver;
import shoppingbasket.service.ShoppingBasketService;
import shoppingbasket.service.dto.res.SelectedMenuResponse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PurchaseDialog extends JDialog implements SelectedMenuObserver {

    JPanel purchasePanel;
    private final ShoppingBasketService shoppingBasketService;
    private final List<SelectedMenuResponse> selectedMenuResponses;

    public PurchaseDialog(JFrame frame, String title, ShoppingBasketService shoppingBasketService){
        super(frame, title);
        this.shoppingBasketService = shoppingBasketService;
        this.selectedMenuResponses = shoppingBasketService.getAllSelectedMenus();
        purchasePanel = new PurchasePanel(shoppingBasketService, this);

        setLayout(new GridLayout(0,2,10,10));
        setSize(1000, 600);


        JPanel menuPanel = new SelectedMenuPanel(shoppingBasketService);
        add(menuPanel);
        add(purchasePanel);

        setResizable(false);
    }

    @Override
    public void update() {

    }
}
