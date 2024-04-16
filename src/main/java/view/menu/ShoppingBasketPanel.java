package view.menu;

import kiosk.service.menu.dto.res.MenuInfo;
import view.purchase.PurchaseDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketPanel extends JPanel implements ActionListener {

    private List<MenuInfo> menus;


    JPanel mainPanel = new JPanel(); // 스크롤 가능한 메인 패널
    JPanel basketItemListPanel = new JPanel(); // 상품 목록 패널
    JLabel totalAmountLabel = new JLabel("총 결제금액: 0원"); //실제 결제금액 계산하도록
    JButton purchaseButton = new JButton("결제");
    PurchaseDialog purchaseDialog;

    private int sumPrice;

//    private List<SelectedMenu> selectedMenus;
    private JFrame parentFrame;

    public ShoppingBasketPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        menus = new ArrayList<>();

//        selectedMenus = new ArrayList<>();
        sumPrice = 0;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        basketItemListPanel.setLayout(new BoxLayout(basketItemListPanel, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout()); // 패널의 레이아웃 설정

        JScrollPane scrollPane = new JScrollPane(basketItemListPanel);
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

    public void addSelectedMenus(MenuInfo menu){
//        menus.add(menu);
//        selectedMenus.add(SelectedMenu.builder()
//                        .name(menu.getName())
//                        .id(menu.getId())
//                        .discountPrice(menu.getDiscountPrice())
//                        .originalPrice(menu.getOriginalPrice())
//                .build());
//
//        addSumPrice(menu);
//        viewSelectedMenus(menu);
//        updateUI();
    }

    public void addSumPrice(MenuInfo menu){
        if(!menu.getDiscountPrice().isEmpty()){
            sumPrice += menu.getDiscountPrice().get();
        } else {
            sumPrice += menu.getOriginalPrice();
        }
        totalAmountLabel.setText("총 결제금액: "+sumPrice+"원");
    }

    public void subSumPrice(MenuInfo menu, int count){
        if(!menu.getDiscountPrice().isEmpty()){
            sumPrice -= menu.getDiscountPrice().get() * count;
        } else {
            sumPrice -= menu.getOriginalPrice() * count;
        }
        totalAmountLabel.setText("총 결제금액: "+sumPrice+"원");
    }

    public void viewSelectedMenus(MenuInfo menu){
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        JPanel firstLinePanel = new JPanel();
        firstLinePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(menu.getName());
        nameLabel.setFont(new Font("Serif", Font.BOLD, 20)); //실제로 추가된 메뉴만 구현하도록.

        JLabel priceLabel = new JLabel("가격: "+menu.getOriginalPrice()+"원");
        String discountPrice = menu.getDiscountPrice().isEmpty() ? "대상 아님" : menu.getDiscountPrice().get()+"원";
        JLabel discountPriceLabel = new JLabel("할인 가격: "+discountPrice); //가격을 계산하도록.

        SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, 100, -1);
        JSpinner quantitySpinner = new JSpinner(quantityModel);

        quantitySpinner.addChangeListener(e -> {
            int oldValue = (int) quantityModel.getPreviousValue();
            int newValue = (int) quantityModel.getValue();
//            int nextValue = (int) quantityModel.getNextValue();
            System.out.println("oldValue : "+oldValue);
            System.out.println("newValue : "+newValue);
//            System.out.println("nextValue : "+nextValue);
//            if(oldValue < newValue){
//                addSumPrice(menu);
//            }
        });

        JButton cancelButton = new JButton("삭제");
        cancelButton.addActionListener(e -> {
            basketItemListPanel.remove(itemPanel);
            basketItemListPanel.revalidate();
            basketItemListPanel.repaint();
            subSumPrice(menu, (int)quantityModel.getValue());
        });

        firstLinePanel.add(nameLabel);
        firstLinePanel.add(priceLabel);
        firstLinePanel.add(discountPriceLabel);
        firstLinePanel.add(quantitySpinner);
        firstLinePanel.add(cancelButton);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String selectedOption = "추가 치즈"; //옵션을 실제 선택된 옵션만 보이도록

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JLabel optionLabel = new JLabel(selectedOption);
        JButton removeButton = new JButton("빼기");
        removeButton.setContentAreaFilled(false);
        removeButton.setBorderPainted(false);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsPanel.removeAll();
                optionsPanel.revalidate();
                optionsPanel.repaint();
            }
        });

        optionPanel.add(optionLabel);
        optionPanel.add(removeButton);
        optionsPanel.add(optionPanel);

        itemPanel.add(firstLinePanel);
        itemPanel.add(optionsPanel);

        basketItemListPanel.add(itemPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(purchaseButton.equals(e.getSource())){
            // 결제 창 구현
            purchaseDialog = new PurchaseDialog(parentFrame, "결제");
            purchaseDialog.setVisible(true);
        }
    }

//    @Builder
//    @AllArgsConstructor
//    private class SelectedMenu {
//        String id;
//        int originalPrice;
//        Optional<Integer> discountPrice;
//        int quantity;
//        String name;
//    }
}
