package view.menu;
import kiosk.service.menu.dto.res.MenuInfo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuGridPanel extends JPanel {
    public MenuGridPanel(List<MenuInfo> menus, ShoppingBasketPanel shoppingBasketPanel) {

        setLayout(new GridLayout(0, 3, 10, 10));  // 3열 그리드, 행은 자동으로 증가

        for (MenuInfo menu : menus) {
            JButton menuButton = new JButton(menu.getName());
            menuButton.addActionListener(e -> {
                // 팝업 생성 및 보여주기
                JPanel popupPanel = new JPanel();
                popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
                JLabel nameLabel = new JLabel("메뉴 이름: " + menu.getName());
                JLabel priceLabel = new JLabel("가격: " + menu.getDiscountPrice().orElse(menu.getOriginalPrice()) + "원");
                JButton addButton = new JButton("추가");

                addButton.addActionListener(e1 -> {
                    // TODO: '추가' 버튼 클릭 시 수행할 구체적인 액션 (예: 메뉴 항목 추가)
                    shoppingBasketPanel.addSelectedMenus(menu);
                    System.out.println(menu.getName() + " 메뉴가 추가되었습니다.");
                    // 팝업 닫기
                    JOptionPane.getRootFrame().dispose();
                });

                popupPanel.add(nameLabel);
                popupPanel.add(priceLabel);
                popupPanel.add(addButton);

                JOptionPane.showMessageDialog(null, popupPanel, "메뉴 정보", JOptionPane.INFORMATION_MESSAGE);
            });
            this.add(menuButton);
        }
    }
}
