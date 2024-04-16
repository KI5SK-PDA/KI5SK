package view.card;

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
        JButton jButton = new CardItem(card);
        jButton.setBounds(20, i*70+10, 940, 60);
        add(jButton);
        return jButton;
    }
}
