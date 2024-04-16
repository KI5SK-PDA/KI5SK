package view.card;

import card.controller.CardController;
import user.model.UserSession;
import view.controller.BasicTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CardFrame extends JFrame implements ActionListener {

    JLabel laCardTitle = new JLabel(UserSession.getUser().getUname()+"님, 원하는 기능을 선택해 주세요!!");
    JButton btnInsertCard = new JButton("카드 추가");
    JButton btnViewCards = new JButton("카드 조회");
    JButton btnChargeCard = new JButton("카드 충전");
    JButton btnDeleteCard = new JButton("카드 삭제");
    JButton btnBack = new JButton("뒤로 가기");

    List<UpdatableJPanel> panels = new ArrayList<>();

    JPanel btns = new JPanel();
    JButton[] buttons = {btnInsertCard, btnViewCards, btnChargeCard, btnDeleteCard};
    BasicTransition toSelect;

    public CardFrame(CardController cardController, BasicTransition toSelect){
        this.toSelect = toSelect;
        panels.add(new CardInsertPanel(cardController));
        panels.add(new CardCheckPanel(cardController));
        panels.add(new CardChargePanel(cardController));
        panels.add(new CardDeletePanel(cardController));

        setTitle("KI5SK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(null);

        laCardTitle.setBounds(15,0,800,60);
        laCardTitle.setFont(new Font("Arial", Font.BOLD, 32));
        add(laCardTitle);

        viewCardNav();
        viewInitPanel();

        btnBack.setBounds(850, 70, 140, 60);
        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        add(btnBack);
        btnBack.addActionListener(this);

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
        if(btnBack.equals(e.getSource())){
            toSelect.switchScreen();
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
