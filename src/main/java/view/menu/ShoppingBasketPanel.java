package view.menu;

import kiosk.service.menu.dto.res.MenuInfo;
import shoppingbasket.controller.SelectedMenuObserver;
import shoppingbasket.service.ShoppingBasketService;
import shoppingbasket.service.dto.res.SelectedMenuResponse;
import shoppingbasket.service.dto.res.SelectedOptionResponse;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingBasketPanel extends JPanel implements ActionListener {

public class ShoppingBasketPanel extends JPanel implements ActionListener, SelectedMenuObserver {

    JPanel mainPanel = new JPanel(); // 스크롤 가능한 메인 패널
    JPanel basketItems = new JPanel(); // 상품 목록 패널
    JLabel totalAmountLabel = new JLabel("총 결제금액: 0원"); //실제 결제금액 계산하도록
    JButton purchaseButton = new JButton("결제");
    PurchaseDialog purchaseDialog;

    private final ShoppingBasketService shoppingBasketService;

//    private List<SelectedMenu> selectedMenus;
    private JFrame parentFrame;

    public ShoppingBasketPanel(ShoppingBasketService shoppingBasketService, JFrame parentFrame) {
        this.shoppingBasketService = shoppingBasketService;
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

    public void viewSelectedMenus(MenuInfo menu){
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        JPanel firstLinePanel = new JPanel();
        firstLinePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        basketItems.add(itemPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(purchaseButton.equals(e.getSource())){
            // 결제 창 구현
            purchaseDialog = new PurchaseDialog(parentFrame, "결제");
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
            add(row);
        }
    }
}
