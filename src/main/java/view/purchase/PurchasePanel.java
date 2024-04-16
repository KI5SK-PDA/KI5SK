package view.purchase;

import card.controller.CardController;
import card.vo.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PurchasePanel extends JPanel implements ActionListener {

    JLabel laTitle = new JLabel("카드를 선택해주세요");
    JLabel laDescription = new JLabel("* 결제하길 원하는 카드 선택하면 바로 결제됩니다.");
    CardController cardController;
    List<Card> cards;
    JPanel cardPanelLayout = new JPanel();
    List<JButton> cardsPanel;

    public PurchasePanel(){
        cardController = new CardController();
        setLayout(new BorderLayout());

        laTitle.setFont(new Font("Arial", Font.BOLD, 32));
        laTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(laTitle, BorderLayout.NORTH);

        add(cardPanelLayout, BorderLayout.CENTER);

        laDescription.setFont(new Font("Arial", Font.BOLD, 16));
        laDescription.setForeground(Color.lightGray);
        cardPanelLayout.add(laDescription, BorderLayout.NORTH);

        getCards();
        viewCardPanel();

    }

    public void getCards(){
        cards = cardController.findCardsByUser();
    }

    public void viewCardPanel(){
        cardPanelLayout.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
        cardsPanel = new ArrayList<>();
        System.out.println();
        for(Card card : cards){
            PurchaseCard purchaseCard = new PurchaseCard(card);
            purchaseCard.addActionListener(this);
            cardsPanel.add(purchaseCard);
            cardPanelLayout.add(purchaseCard);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton jButton: cardsPanel){
            if(jButton.equals(e.getSource())){
                // TODO 결제 진행코드
            }
        }
    }
}
