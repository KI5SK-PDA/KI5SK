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
    JLabel laDescription = new JLabel("* 비밀번호를 입력하고 결제를 원하는 카드를 선택하면 바로 결제됩니다.");
    JPasswordField pfPassword = new JPasswordField();
    CardController cardController;
    List<Card> cards;
    JPanel cardPanelLayout = new JPanel();
    List<JButton> cardPanels;

    public PurchasePanel(){
        cardController = new CardController();
        setLayout(new BorderLayout());

        laTitle.setFont(new Font("Arial", Font.BOLD, 32));
        laTitle.setHorizontalAlignment(SwingConstants.CENTER);
        laTitle.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        add(laTitle, BorderLayout.NORTH);

        add(cardPanelLayout, BorderLayout.CENTER);

        laDescription.setFont(new Font("Arial", Font.BOLD, 16));
        laDescription.setForeground(Color.lightGray);
        cardPanelLayout.add(laDescription, BorderLayout.NORTH);

        pfPassword.setFont(new Font("Arial", Font.BOLD, 32));
        pfPassword.setPreferredSize(new Dimension(450, 60));
        cardPanelLayout.add(pfPassword, BorderLayout.NORTH);

        getCards();
        viewCardPanel();

    }

    public void getCards(){
        cards = cardController.findCardsByUser();
    }

    public void viewCardPanel(){
        cardPanelLayout.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
        cardPanels = new ArrayList<>();
        System.out.println();
        for(Card card : cards){
            PurchaseCard purchaseCard = new PurchaseCard(card);
            purchaseCard.addActionListener(this);
            cardPanels.add(purchaseCard);
            cardPanelLayout.add(purchaseCard);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<cards.size(); i++){
            if(cardPanels.get(i).equals(e.getSource())){
                String cardNumber = cards.get(i).getCno();
                String cardPassword = new String(pfPassword.getPassword());
                // TODO 결제 진행코드
                pfPassword.setText("");
            }
        }
    }
}
