package card.view;

import card.controller.CardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CardView extends JFrame implements ActionListener {

    JLabel laCardTitle = new JLabel("카드 시스템입니다.");
    JButton btnInsertCard = new JButton("카드 추가");
    JButton btnViewCards = new JButton("카드 조회");
    JButton btnChargeCard = new JButton("카드 충전");
    JButton btnDeleteCard = new JButton("카드 삭제");

    List<UpdatableJPanel> panels = new ArrayList<>();
    JButton[] buttons = {btnInsertCard, btnViewCards, btnChargeCard, btnDeleteCard};

    private CardController cardController;

    public CardView(CardController cardController){
        panels.add(new CardInsertPanel(cardController));
        panels.add(new CardCheckPanel(cardController));
        panels.add(new CardChargePanel(cardController));
        panels.add(new CardDeletePanel(cardController));

        this.cardController = cardController;
        setTitle("KI5SK입니다.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(null);

        laCardTitle.setBounds(10,0,300,60);
        laCardTitle.setFont(new Font("Arial", Font.BOLD, 32));
        add(laCardTitle);

        viewCardNav();
        viewInitPanel();

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].equals(e.getSource())) {
                showPanel(panels.get(i));
            } else {
                hidePanel(panels.get(i));
            }
        }
    }

    private void showPanel(UpdatableJPanel panel) {
        panel.updateData();
        panel.setVisible(true);
    }

    private void hidePanel(JPanel panel) {
        panel.setVisible(false);
    }

    public void viewInitPanel(){
        for(int i=0; i<panels.size(); i++){
            panels.get(i).setBounds(10,140,980,600);
            add(panels.get(i));
            panels.get(i).setVisible(i==0);
        }
    }

    public void viewCardNav(){
        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.addActionListener(this);
            button.setBounds(10 + i * 210, 70, 200, 60);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            add(button);
        }
    }

}
