package card.view;

import card.controller.CardController;
import card.vo.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardCheckPanel extends UpdatableJPanel {
    private CardController cardController;
    private List<Card> cards;
    private List<JButton> jButtons;

    public CardCheckPanel(CardController cardController){
        this.cardController = cardController;
        cards = cardController.findCardsByUser();
        setLayout(null);
        setBackground(Color.WHITE);
    }

    @Override
    void updateData() {
        if(jButtons != null && !jButtons.isEmpty()){
            for(JButton jButton: jButtons){
                remove(jButton);
            }
        }
        jButtons = new ArrayList<>();
        cards = cardController.findCardsByUser();
        for(int i=0; i<cards.size(); i++){
            System.out.println(cards.get(i).toString());
            jButtons.add(createCardView(i, cards.get(i)));
        }
    }

    public JButton createCardView(int i, Card card){
        JButton jButton = new JButton();
        jButton.setSize(940, 60);
        jButton.setBackground(new Color(219, 219, 219));
        jButton.setBounds(20, i*70+10, 940, 60);
        jButton.setLayout(null);
        add(jButton);

        JLabel laCardNo = new JLabel(card.getCno());
        laCardNo.setBounds(20, 15, 300, 30);
        laCardNo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel laCardBank = new JLabel(card.getCompany().getCname());
        laCardBank.setBounds(320, 15, 300, 30);
        laCardBank.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel laMoney = new JLabel(Integer.toString(card.getMoney().toInt())+"원");
        laMoney.setBounds(720, 15, 300, 30);
        laMoney.setFont(new Font("Arial", Font.PLAIN, 24));

        jButton.add(laCardNo);
        jButton.add(laCardBank);
        jButton.add(laMoney);

        return jButton;
    }
}
