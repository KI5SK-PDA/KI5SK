package view.card;

import card.controller.CardController;
import card.vo.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardInsertPanel extends UpdatableJPanel implements ActionListener {

    JButton btnShinhanBank = new JButton("신한은행");
    JButton btnWooriBank = new JButton("우리은행");
    JButton btnKBBank = new JButton("국민은행");
    JButton btnNHBank = new JButton("농협은행");
    JButton btnHanaBank = new JButton("하나은행");
    JLabel laDescription = new JLabel("* 발급을 원하는 금융회사를 선택하고 비밀번호를 입력해주세요");
    JLabel laPassword = new JLabel("비밀번호");
    JPasswordField pfPassword = new JPasswordField();
    JButton btnCreateCard = new JButton("카드 발급");

    Font font = new Font("Arial", Font.BOLD, 24);
    JButton[] banks = {btnShinhanBank, btnWooriBank, btnKBBank, btnNHBank, btnHanaBank};

    private String selectedBank = "";

    CardController cardController;

    public CardInsertPanel(CardController cardController){
        System.out.println(cardController);
        this.cardController = cardController;

        setLayout(null);
        setBackground(Color.white);
        laDescription.setBounds(15,10, 500, 30);
        laDescription.setFont(new Font("Arial", Font.PLAIN, 15));

        btnShinhanBank.setBounds(10,50,150,60);
        btnWooriBank.setBounds(170, 50, 150, 60);
        btnKBBank.setBounds(330, 50, 150, 60);
        btnNHBank.setBounds(490, 50, 150, 60);
        btnHanaBank.setBounds(650, 50, 150, 60);

        laPassword.setBounds(20, 140, 100, 60);
        laPassword.setFont(font);

        pfPassword.setBounds(130, 140, 680, 60);
        pfPassword.setFont(font);

        btnCreateCard.setBounds(20, 230, 780, 60);

        btnShinhanBank.addActionListener(this);
        btnWooriBank.addActionListener(this);
        btnKBBank.addActionListener(this);
        btnNHBank.addActionListener(this);
        btnHanaBank.addActionListener(this);
        btnCreateCard.addActionListener(this);

        add(laDescription);
        add(btnShinhanBank);
        add(btnWooriBank);
        add(btnKBBank);
        add(btnNHBank);
        add(btnHanaBank);
        add(laPassword);
        add(pfPassword);
        add(btnCreateCard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton bank : banks) {
            if (bank.equals(e.getSource())) {
                selectedBank = bank.getText();
                System.out.println(selectedBank);
            }
        }
        if(btnCreateCard.equals(e.getSource())){
            String password = new String(pfPassword.getPassword());
            Card card = cardController.insertCard(selectedBank, password);
            System.out.println(card.toString());
            pfPassword.setText("");
        }
    }
}