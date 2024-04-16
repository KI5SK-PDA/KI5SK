package view.menu;

import shoppingbasket.controller.SelectedMenuObserver;
import shoppingbasket.controller.SelectedMenuSubject;
import shoppingbasket.controller.ShoppingBasketController;
import shoppingbasket.service.ShoppingBasketService;
import shoppingbasket.service.dto.res.SelectedMenuResponse;
import shoppingbasket.service.dto.res.SelectedOptionResponse;
import view.purchase.PurchaseDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingBasketPanel extends JPanel implements ActionListener, SelectedMenuObserver {

    JPanel mainPanel = new JPanel(); // 스크롤 가능한 메인 패널
    JPanel basketItems = new JPanel(); // 상품 목록 패널
    JLabel totalAmountLabel = new JLabel("총 결제금액: 0원"); //실제 결제금액 계산하도록
    JButton purchaseButton = new JButton("결제");
    PurchaseDialog purchaseDialog;

    private final SelectedMenuSubject selectedMenuSubject;
    private final ShoppingBasketService shoppingBasketService;

    private JFrame parentFrame;

    public ShoppingBasketPanel(ShoppingBasketController shoppingBasketController, JFrame parentFrame) {
        this.selectedMenuSubject = shoppingBasketController;
        this.shoppingBasketService = shoppingBasketController;
        this.parentFrame = parentFrame;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        basketItems.setLayout(new BoxLayout(basketItems, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout()); // 패널의 레이아웃 설정

        JScrollPane scrollPane = new JScrollPane(basketItems);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane);
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(totalAmountLabel);

        JPanel purchasePanel = new JPanel();
        purchaseButton.setFont(new Font("Arial", Font.BOLD, 30));
        purchaseButton.setPreferredSize(new Dimension(300, 40));
        purchasePanel.add(purchaseButton);
        mainPanel.add(purchasePanel);

        purchaseButton.addActionListener(this);

        this.add(mainPanel, BorderLayout.CENTER); //결제 onClick 이벤트 달기
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(purchaseButton.equals(e.getSource())){
            // 결제 창 구현

            purchaseDialog = new PurchaseDialog(parentFrame, "결제", shoppingBasketService);
            selectedMenuSubject.registerObserver(purchaseDialog);
            purchaseDialog.setVisible(true);
        }
    }

    @Override
    public void update() {
        basketItems.removeAll();
        int totalPrice = 0;
        for(SelectedMenuResponse menu: shoppingBasketService.getAllSelectedMenus()) {
            basketItems.add(new SelectedMenuItemPanel(menu));
            int price = 0;

            if (menu.getDiscountPrice().isEmpty()) {
                price += menu.getOriginalPrice();
            } else {
                price += menu.getDiscountPrice().get();
            }

           for (SelectedOptionResponse option: menu.getSelectedOptions()) {
               price += option.getPrice();
           }

           totalPrice += price * menu.getQuantity();
        }

        totalAmountLabel.setText("총 결제금액: "+totalPrice+"원");
        updateUI();
    }

    private class SelectedMenuItemPanel extends JPanel {
        private SelectedMenuResponse menu;

        SelectedMenuItemPanel(SelectedMenuResponse menu) {
            this.menu = menu;
            Box col = Box.createVerticalBox();

            Box row = Box.createHorizontalBox();

            // 메뉴 이름
            String menuInfo = menu.getName();
            if (menu.getDiscountPrice().isEmpty()) {
                menuInfo += String.format("    %d 원", menu.getOriginalPrice());
            } else {
                menuInfo += String.format("    %d 원 (할인가)", menu.getDiscountPrice().get());
            }

            JLabel infoLabel = new JLabel(menuInfo);
            JButton deleteButton = new JButton("삭제");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shoppingBasketService.removeSelectedMenu(menu.getMenuId());
                }
            });

            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(menu.getQuantity(), 1, 100, 1));
            quantitySpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    try {
                        int value = (Integer) quantitySpinner.getValue();
                        if (value < menu.getQuantity()) {
                            shoppingBasketService.decrementQuantityById(menu.getMenuId());
                        } else if (value > menu.getQuantity()) {
                            shoppingBasketService.incrementQuantityById(menu.getMenuId());
                        }
                    } catch (Exception exception) {
                        System.out.println("오류 발생");
                    }
                }
            });

            row.add(infoLabel);
            row.add(quantitySpinner);
            row.add(deleteButton);


            col.add(row);
            for(SelectedOptionResponse option: menu.getSelectedOptions()) {
                Box options = Box.createHorizontalBox();

                JLabel optionLabel = new JLabel(String.format("%s: +%d원", option.getName(), option.getPrice()));
                JButton deleteOptionButton = new JButton("삭제");
                deleteOptionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        shoppingBasketService.removeSelectedOption(menu.getMenuId(), option.getOptionId());
                    }
                });

                options.add(optionLabel);
                options.add(deleteOptionButton);
                col.add(options);
            }
            add(col);
        }
    }
}
