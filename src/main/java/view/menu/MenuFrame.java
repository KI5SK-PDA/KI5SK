package view.menu;

import kiosk.controller.KioskController;
import kiosk.service.menu.GetMenuService;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.menu.dto.res.MenuInfo;
import kiosk.service.store.CommonStoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MenuFrame extends JFrame {
    private final GetMenuService menuService = KioskController.newInstance();
    private final CommonStoreService storeService = KioskController.newInstance();

    public MenuFrame(String storeId) {
        setTitle("메뉴");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1, 20,20));

        Box mainBox = Box.createHorizontalBox();

        Box sideBox = Box.createVerticalBox();

        JLabel header = new JLabel(storeService.getStoreById(storeId).getName());
        header.setFont(new Font("Arial", Font.BOLD, 40));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        sideBox.add(header);
        sideBox.add(new ShoppingBasketPanel());


        // 메뉴가져오기 이후라 가정
        Box menuList = Box.createHorizontalBox();
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
                .name("사이드")
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
                .build()
        );
        Box categoryTaps = Box.createHorizontalBox();


        categoryNames.forEach((category) -> {
            int count = 0;
            JPanel tap = new CategoryTapButton(category.getName(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("yes");
                }
            });

            categoryTaps.add(tap);
            JPanel menuPanel = new MenuGridPanel(category.getMenus());
            menuList.add(menuPanel);
        });



        menuList.add(categoryTaps);
        JScrollPane menuScrollPane = new JScrollPane(menuList);
        mainBox.add(menuScrollPane);
        mainBox.add(sideBox);

        add(mainBox);
        setSize(1200, 1080);
        setVisible(true);
    }
}
