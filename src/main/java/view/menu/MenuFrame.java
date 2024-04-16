package view.menu;

import kiosk.controller.KioskController;
import kiosk.service.menu.GetMenuService;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.menu.dto.res.MenuInfo;
import kiosk.service.store.CommonStoreService;
import view.controller.BasicTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MenuFrame extends JFrame {
    private final GetMenuService menuService = KioskController.newInstance();
    private final CommonStoreService storeService = KioskController.newInstance();
    private List<JButton> taps = new ArrayList<>();
    private List<JPanel> menuPanels = new ArrayList<>();
    private Box menuList = Box.createVerticalBox();

    public MenuFrame(String storeId, BasicTransition backToStore) {
        setTitle("메뉴");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1, 20,20));

        Box mainBox = Box.createHorizontalBox();

        Box sideBox = Box.createVerticalBox();

        Box headerBox = Box.createHorizontalBox();
        JLabel header = new JLabel(storeService.getStoreById(storeId).getName());
        header.setFont(new Font("Arial", Font.BOLD, 40));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        JButton backButton = new JButton("뒤로 가기");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToStore.switchScreen();
            }
        });

        headerBox.add(header);
        headerBox.add(backButton);
        sideBox.add(headerBox);

        ShoppingBasketPanel shoppingBasketPanel = new ShoppingBasketPanel(this);
        shoppingBasketPanel.setMaximumSize(new Dimension(480, 1080)); // 가로 480px, 세로는 제한 없음
        sideBox.add(shoppingBasketPanel);



        // 메뉴가져오기 이후라 가정
        List<CategoryInfo> categoryNames = Arrays.asList(
            CategoryInfo.builder()
                .name("단품")
                .menus(Arrays.asList(
                    MenuInfo.builder()
                        .name("짜장면")
                        .id("110-01-000")
                        .originalPrice(4000)
                        .discountPrice(Optional.of(3000))
                        .discountInfo(Optional.of("1000원 할인"))
                        .build(),
                    MenuInfo.builder()
                        .name("짬뽕")
                        .id("110-01-001")
                        .originalPrice(5000)
                        .discountPrice(Optional.of(4000))
                        .discountInfo(Optional.of("1000원 할인"))
                        .build(),
                    MenuInfo.builder()
                        .name("탕수육")
                        .id("110-01-002")
                        .originalPrice(14000)
                        .discountPrice(Optional.empty())
                        .discountInfo(Optional.empty())
                        .build()
                ))
                .build(),
            CategoryInfo.builder()
                .name("세트")
                .menus(Arrays.asList(
                    MenuInfo.builder()
                        .name("짜장면 2+ 탕수육")
                        .id("110-01-000")
                        .originalPrice(10000)
                        .discountPrice(Optional.of(3000))
                        .discountInfo(Optional.of("1000원 할인"))
                        .build(),
                    MenuInfo.builder()
                        .name("짬뽕2+ 탕수육")
                        .id("110-01-001")
                        .originalPrice(15000)
                        .discountPrice(Optional.of(4000))
                        .discountInfo(Optional.of("1000원 할인"))
                        .build(),
                    MenuInfo.builder()
                        .name("탕수육+양장피")
                        .id("110-01-002")
                        .originalPrice(24000)
                        .discountPrice(Optional.empty())
                        .discountInfo(Optional.empty())
                        .build()
                ))
                .build(),
            CategoryInfo.builder()
                .name("사이드")
                .menus(Arrays.asList(
                    MenuInfo.builder()
                        .name("단무지")
                        .id("110-01-000")
                        .originalPrice(4000)
                        .discountPrice(Optional.of(3000))
                        .discountInfo(Optional.of("1000원 할인"))
                        .build(),
                    MenuInfo.builder()
                        .name("군만두")
                        .id("110-01-001")
                        .originalPrice(5000)
                        .discountPrice(Optional.of(4000))
                        .discountInfo(Optional.of("1000원 할인"))
                        .build(),
                    MenuInfo.builder()
                        .name("콜라")
                        .id("110-01-002")
                        .originalPrice(14000)
                        .discountPrice(Optional.empty())
                        .discountInfo(Optional.empty())
                        .build()
                ))
                .build()
        );

        Box categoryTaps = Box.createHorizontalBox();
        for(int i=0; i<categoryNames.size(); i++) {
            CategoryInfo categoryInfo = categoryNames.get(i);
            JButton tapButton = new JButton(categoryInfo.getName());
            JPanel tap = new CategoryTapButton(tapButton, new TapChangeListener());

            taps.add(tapButton);
            categoryTaps.add(tap);
            JPanel menuPanel = new MenuGridPanel(categoryInfo.getMenus(), shoppingBasketPanel);
            menuPanel.setVisible(false);
            menuPanels.add(menuPanel);
        }

        menuList.add(categoryTaps);
        menuPanels.get(0).setVisible(true);
        for (JPanel menuPanel: menuPanels)
            menuList.add(menuPanel);

        JScrollPane menuScrollPane = new JScrollPane(menuList);
        mainBox.add(menuScrollPane);
        mainBox.add(sideBox);

        add(mainBox);
        setSize(1200, 1080);
        setVisible(true);
    }

    private class TapChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("----debug----");
            for (int i=0;i<taps.size();i++) {
                if (e.getSource().equals(taps.get(i))) {
                    menuPanels.get(i).setVisible(true);
                } else {
                    menuPanels.get(i).setVisible(false);
                    System.out.println("no"+i);
                }
            }
        }
    }
}
/*
* 수정해야 할 부분:
* grid 달아놓은거 크기 깨지는 부분 (menugridpanel)
* */