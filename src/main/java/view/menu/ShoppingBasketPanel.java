package view.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingBasketPanel extends JPanel {
    public ShoppingBasketPanel() {
        Box mainBox = Box.createVerticalBox();

        Box basketItemList = Box.createVerticalBox();

        for (int i = 0; i < 20; i++) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

            JPanel firstLinePanel = new JPanel();
            firstLinePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel nameLabel = new JLabel("메뉴 " + i); // 여기, 실제로 주문된 음식만 들어가야해요 (임시로 넣어둔 더미 데이터)
            nameLabel.setFont(new Font("Serif", Font.BOLD, 20));

            JLabel priceLabel = new JLabel("가격: 10000원");
            JLabel discountPriceLabel = new JLabel("할인 가격: 8000원"); //실제 가격과 할인 가격을 계산해서 위치시키면 됨

            SpinnerModel quantityModel = new SpinnerNumberModel(1, 1, 100, 1);
            JSpinner quantitySpinner = new JSpinner(quantityModel);

            JButton cancelButton = new JButton("삭제");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    basketItemList.remove(itemPanel); // 주문한 음식을 삭제할 수 있는 기능
                    basketItemList.revalidate();
                    basketItemList.repaint();
                    // 가격 재산정 로직은 구현해주시면 되겠습니다.
                }
            });

            firstLinePanel.add(nameLabel);
            firstLinePanel.add(priceLabel);
            firstLinePanel.add(discountPriceLabel);
            firstLinePanel.add(quantitySpinner);
            firstLinePanel.add(cancelButton);

            JPanel optionsPanel = new JPanel();
            optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            // 옵션이 하나만 선택되므로, ArrayList 대신 단일 객체를 사용합니다.
            String selectedOption = "추가 치즈"; // 예시로 추가 치즈를 선택된 옵션으로 가정

            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

            JLabel optionLabel = new JLabel(selectedOption);
            JButton removeButton = new JButton("빼기");
            removeButton.setContentAreaFilled(false);
            removeButton.setBorderPainted(false);
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    optionsPanel.removeAll(); // 옵션 삭제 기능 구현, 하나의 옵션만 있으므로 전체 삭제
                    optionsPanel.revalidate();
                    optionsPanel.repaint();
                    // 옵션 삭제에 따른 가격 재산정도 고려해주셔야 합니다.
                }
            });

            optionPanel.add(optionLabel);
            optionPanel.add(removeButton);
            optionsPanel.add(optionPanel);

            itemPanel.add(firstLinePanel);
            itemPanel.add(optionsPanel);

            basketItemList.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(basketItemList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainBox.add(scrollPane);

        JLabel totalAmountLabel = new JLabel("총 결제금액: 0원");
        // 총 결제금액을 계산하는 로직은 구현부탁드려요
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainBox.add(totalAmountLabel);

        JPanel purchasePanel = new JPanel();
        JButton purchaseButton = new JButton("결제");
        purchaseButton.setFont(new Font("Arial", Font.BOLD, 30));
        purchaseButton.setPreferredSize(new Dimension(300, 40));
        purchasePanel.add(purchaseButton);
        mainBox.add(purchasePanel);

        add(mainBox);
    }
}
