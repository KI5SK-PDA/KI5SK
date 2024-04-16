package view.menu;

import kiosk.service.menu.dto.res.MenuInfo;
import kiosk.service.menu.dto.res.OptionMenuInfo;
import shoppingbasket.controller.ShoppingBasketController;
import shoppingbasket.service.ShoppingBasketService;
import shoppingbasket.service.dto.req.AddSelectedMenuRequest;
import shoppingbasket.service.dto.req.AddSelectedOptionRequest;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuGridPanel extends JPanel {
    private final ShoppingBasketService shoppingBasketService;
    private final Map<String,List<OptionMenuInfo>> selectOptions;

    public MenuGridPanel(List<MenuInfo> menus, ShoppingBasketController shoppingBasketController) {
        this.shoppingBasketService = shoppingBasketController;
        setLayout(new GridLayout(0, 3, 10, 10));  // 3열 그리드, 행은 자동으로 증가
        selectOptions = new HashMap<>();

        for (MenuInfo menu : menus) {
            JButton menuButton = new JButton(menu.getName());
            menuButton.addActionListener(e -> {
                // 팝업 생성 및 보여주기
                JPanel popupPanel = new JPanel();
                popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
                JLabel nameLabel = new JLabel("메뉴 이름: " + menu.getName());
                String priceText = "가격: "+menu.getOriginalPrice()+"원";
                if (menu.getDiscountInfo().isPresent()) {
                    priceText += String.format("->%d 원(%s)", menu.getDiscountPrice().get(), menu.getDiscountInfo().get());
                }

                selectOptions.put(menu.getId(), new ArrayList<>());

                JLabel priceLabel = new JLabel(priceText);
                JButton addButton = new JButton("추가");

                addButton.addActionListener(e1 -> {
                    shoppingBasketController.addSelectedMenu(AddSelectedMenuRequest.builder()
                            .menuId(menu.getId())
                            .discountPrice(menu.getDiscountPrice())
                            .originalPrice(menu.getOriginalPrice())
                            .name(menu.getName())
                        .build());

                    for(OptionMenuInfo optionMenuInfo: selectOptions.get(menu.getId())) {
                        shoppingBasketController.addOptions(AddSelectedOptionRequest.builder()
                                .menuId(menu.getId())
                                .price(optionMenuInfo.getPrice())
                                .optionId(optionMenuInfo.getId())
                                .name(optionMenuInfo.getName())
                            .build());
                        System.out.println(optionMenuInfo.getName());
                    }

                    // 팝업 닫기
                    JOptionPane.getRootFrame().dispose();
                });

                popupPanel.add(nameLabel);
                popupPanel.add(priceLabel);

                for(String optionGroupId: menu.getOptionGroupMap().keySet().stream().collect(Collectors.toList())) {
                    popupPanel.add(new OptionPanel(
                        menu.getOptionGroupMap().get(optionGroupId),
                        menu.getOptionMenuMap().get(optionGroupId),
                        menu.getId()
                    ));
                }

                popupPanel.add(addButton);

                JOptionPane.showMessageDialog(null, popupPanel, "메뉴 정보", JOptionPane.INFORMATION_MESSAGE);
            });
            this.add(menuButton);
        }
    }

    private class OptionPanel extends JPanel {
        public OptionPanel(String optionGroup, List<OptionMenuInfo> optionMenuInfo, String menuId) {
            setLayout(new FlowLayout());

            for (int i = 0; i < optionMenuInfo.size(); i++) {
                JCheckBox checkBox = new JCheckBox(optionMenuInfo.get(i).getName()+":"+optionMenuInfo.get(i).getPrice()+" 원");
                checkBox.addItemListener(new OptionMenuSelectListner(menuId, optionMenuInfo.get(i)));
                add(checkBox);
            }

            setBorder(new TitledBorder(new LineBorder(Color.gray,1), optionGroup));
        }
    }

    private class OptionMenuSelectListner implements ItemListener {
        private OptionMenuInfo optionMenuInfo;
        private String menuId;
        OptionMenuSelectListner(String menuId, OptionMenuInfo optionMenuInfo) {
            this.menuId = menuId;
            this.optionMenuInfo = optionMenuInfo;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectOptions.get(menuId).add(optionMenuInfo);
            } else {
                selectOptions.get(menuId).remove(optionMenuInfo);
            }
        }
    }
}
