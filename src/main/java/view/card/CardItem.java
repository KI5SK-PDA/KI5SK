package view.card;

import card.vo.Card;

import javax.swing.*;
import java.awt.*;

public class CardItem extends JButton {
    public CardItem(Card card){
        setSize(940, 60);
        setBackground(new Color(219, 219, 219));
        setLayout(null);

        JLabel laCardNo = new JLabel(card.getCno());
        laCardNo.setBounds(20, 15, 300, 30);
        laCardNo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel laCardBank = new JLabel(card.getCompany().getCname());
        laCardBank.setBounds(320, 15, 300, 30);
        laCardBank.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel laMoney = new JLabel(Integer.toString(card.getMoney().toInt())+"원");
        laMoney.setBounds(720, 15, 300, 30);
        laMoney.setFont(new Font("Arial", Font.PLAIN, 24));

        add(laCardNo);
        add(laCardBank);
        add(laMoney);
    }
}
