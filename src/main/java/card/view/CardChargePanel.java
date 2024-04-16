package card.view;

import card.controller.CardController;
import card.vo.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CardChargePanel extends UpdatableJPanel implements ActionListener {

    private CardController cardController;
    private java.util.List<Card> cards;
    private List<JButton> jButtons;

    JLabel laDescription = new JLabel("* 충전하길 원하는 금액을 입력 후 충전할 카드를 선택해주세요");
    JLabel laMoney = new JLabel("충전할 금액");
    JTextField tfMoney = new JTextField();
    Font font = new Font("Arial", Font.BOLD, 24);

    public CardChargePanel(CardController cardController){
        this.cardController = cardController;
        cards = cardController.findCardsByUser();
        setLayout(null);
        setBackground(Color.WHITE);

        laDescription.setBounds(15,10, 500, 20);
        laDescription.setFont(new Font("Arial", Font.PLAIN, 15));

        laMoney.setBounds(20, 40, 140, 60);
        laMoney.setFont(font);

        tfMoney.setBounds(150, 40, 680, 60);
        tfMoney.setFont(font);

        add(laDescription);
        add(laMoney);
        add(tfMoney);
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
        jButton.setBounds(20, i*70+10+100, 940, 60);
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

        jButton.addActionListener(this);

        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i< jButtons.size(); i++){
            if(jButtons.get(i).equals(e.getSource())){
                Card updatedCard = cardController.chargeCard(cards.get(i).getCno(), tfMoney.getText());
                remove(jButtons.get(i));
                JButton updatedButton = createCardView(i, updatedCard);
                jButtons.set(i, updatedButton);
                add(updatedButton);
                tfMoney.setText("");
                updateUI();
            }
        }
    }
}
