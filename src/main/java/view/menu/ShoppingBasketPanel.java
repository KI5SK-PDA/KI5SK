package view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingBasketPanel extends JPanel {
    public ShoppingBasketPanel() {
        this.setLayout(new BorderLayout()); // 패널의 레이아웃 설정

        JPanel mainPanel = new JPanel(); // 스크롤 가능한 메인 패널
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel basketItemListPanel = new JPanel(); // 상품 목록 패널
        basketItemListPanel.setLayout(new BoxLayout(basketItemListPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 20; i++) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

            JPanel firstLinePanel = new JPanel();
            firstLinePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel nameLabel = new JLabel("메뉴 " + i);
            nameLabel.setFont(new Font("Serif", Font.BOLD, 20)); //실제로 추가된 메뉴만 구현하도록.

            JLabel priceLabel = new JLabel("가격: 10000원");
            JLabel discountPriceLabel = new JLabel("할인 가격: 8000원"); //가격을 계산하도록.

            SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, 100, 1);
            JSpinner quantitySpinner = new JSpinner(quantityModel);

            JButton cancelButton = new JButton("삭제");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    basketItemListPanel.remove(itemPanel);
                    basketItemListPanel.revalidate();
                    basketItemListPanel.repaint();
                }
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

        JScrollPane scrollPane = new JScrollPane(basketItemListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane);

        JLabel totalAmountLabel = new JLabel("총 결제금액: 0원"); //실제 결제금액 계산하도록
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(totalAmountLabel);

        JPanel purchasePanel = new JPanel();
        JButton purchaseButton = new JButton("결제");
        purchaseButton.setFont(new Font("Arial", Font.BOLD, 30));
        purchaseButton.setPreferredSize(new Dimension(300, 40));
        purchasePanel.add(purchaseButton);
        mainPanel.add(purchasePanel);

        this.add(mainPanel, BorderLayout.CENTER); //결제 onClick 이벤트 달기
    }
}
