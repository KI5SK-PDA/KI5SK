package view.purchase;

import card.vo.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PurchaseCard extends JButton {

    public PurchaseCard(Card card){
        setLayout(new BorderLayout());
        setBackground(Color.YELLOW);

        setPreferredSize(new Dimension(450, 60));

        JLabel laCardNo = new JLabel(card.getCno());
        laCardNo.setFont(new Font("Arial", Font.BOLD, 18));
        add(laCardNo, BorderLayout.WEST);

        // (임시) 밑의 띄어쓰기 간격은 laCardNo이랑 간격 띄우기 위해서
        JLabel laCardBank = new JLabel("          "+card.getCompany().getCname());
        laCardBank.setFont(new Font("Arial", Font.BOLD, 18));
        add(laCardBank, BorderLayout.CENTER);

        JLabel laMoney = new JLabel(card.getMoney().toInt()+"원");
        laMoney.setFont(new Font("Arial", Font.BOLD, 18));
        add(laMoney, BorderLayout.EAST);

    }
}
