package view.purchase;

import card.vo.Card;
import shoppingbasket.service.ShoppingBasketService;
import shoppingbasket.service.dto.res.SelectedMenuResponse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectedMenuPanel extends JPanel {

    JLabel laTitle = new JLabel("결제 예정 메뉴");
    JLabel laDescription = new JLabel("* 카드 할인까지 들어가면 최종 결제 금액은 다를 수 있습니다.");
    private final List<SelectedMenuResponse> selectedMenuResponses;
    JPanel menuPanelLayout = new JPanel();
    List<JButton> menuPanels;

    public SelectedMenuPanel(ShoppingBasketService shoppingBasketService){
        this.selectedMenuResponses = shoppingBasketService.getAllSelectedMenus();
        setLayout(new BorderLayout());

        laTitle.setFont(new Font("Arial", Font.BOLD, 32));
        laTitle.setHorizontalAlignment(SwingConstants.CENTER);
        laTitle.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        add(laTitle, BorderLayout.NORTH);

        add(menuPanelLayout, BorderLayout.CENTER);

        laDescription.setFont(new Font("Arial", Font.BOLD, 16));
        laDescription.setForeground(Color.lightGray);
        menuPanelLayout.add(laDescription, BorderLayout.NORTH);

        viewMenuPanel();
    }

    public void viewMenuPanel(){
        menuPanelLayout.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
        menuPanels = new ArrayList<>();
        for(SelectedMenuResponse selectedMenuResponse : selectedMenuResponses){
            SelectedMenuItem selectedMenuItem = new SelectedMenuItem(selectedMenuResponse);
            menuPanels.add(selectedMenuItem);
            menuPanelLayout.add(selectedMenuItem);
        }
    }
}
