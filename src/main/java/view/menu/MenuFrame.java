package view.menu;

import kiosk.controller.KioskController;
import kiosk.service.menu.GetMenuService;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.store.CommonStoreService;
import shoppingbasket.controller.ShoppingBasketController;
import view.controller.BasicTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuFrame extends JFrame {
    private final GetMenuService menuService = KioskController.newInstance();
    private final CommonStoreService storeService = KioskController.newInstance();
    private List<JButton> taps = new ArrayList<>();
    private List<JPanel> menuPanels = new ArrayList<>();
    private Box menuList = Box.createVerticalBox();
    private final GetMenuService getMenuService = KioskController.newInstance();
    private final ShoppingBasketController shoppingBasketController;

    public MenuFrame(String storeId, BasicTransition backToStore, ShoppingBasketController shoppingBasketController) {
        this.shoppingBasketController = shoppingBasketController;
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
                shoppingBasketController.clear();
                backToStore.switchScreen();
            }
        });

        headerBox.add(header);
        headerBox.add(backButton);
        sideBox.add(headerBox);

        ShoppingBasketPanel shoppingBasketPanel = new ShoppingBasketPanel(shoppingBasketController, this);
        shoppingBasketController.registerObserver(shoppingBasketPanel);
        shoppingBasketPanel.setMaximumSize(new Dimension(480, 1080)); // 가로 480px, 세로는 제한 없음
        sideBox.add(shoppingBasketPanel);

        // 메뉴가져오기 이후라 가정
        List<CategoryInfo> categoryInfos = getMenuService.getAllMenusByStoreId(storeId);
        System.out.println(categoryInfos.size());
        Box categoryTaps = Box.createHorizontalBox();
        for(int i=0; i<categoryInfos.size(); i++) {
            CategoryInfo categoryInfo = categoryInfos.get(i);
            JButton tapButton = new JButton(categoryInfo.getName());
            JPanel tap = new CategoryTapButton(tapButton, new TapChangeListener());

            taps.add(tapButton);
            categoryTaps.add(tap);
            JPanel menuPanel = new MenuGridPanel(categoryInfo.getMenus(), shoppingBasketController);
            menuPanel.setVisible(false);
            menuPanels.add(menuPanel);
        }

        menuList.add(categoryTaps);
        if (menuPanels.size() > 0) {
            menuPanels.get(0).setVisible(true);
        }

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