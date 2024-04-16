package view.select;

import view.controller.BasicTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFrame extends JFrame implements ActionListener {

    JButton btnCard = new JButton("카드시스템");
    JButton btnKiosk = new JButton("키오스크");
    JButton btnMain = new JButton("재로그인");
    JPanel jPanel = new JPanel();
    JLabel laUser;
    private final BasicTransition toStore;
    private final BasicTransition toCard;
    private final BasicTransition toUser;

    public SelectFrame(BasicTransition toStore, BasicTransition toCard, BasicTransition toUser){
        this.toStore = toStore;
        this.toCard = toCard;
        this.toUser = toUser;

        setTitle("KI5SK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(null);
        setBackground(Color.lightGray);

        btnCard.addActionListener(this);
        btnKiosk.addActionListener(this);
        btnMain.addActionListener(this);

        jPanel.setBounds(20, 20, 960, 730);
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);

        btnCard.setBounds(280, 180, 400, 100);
        btnCard.setFont(new Font("Arial", Font.BOLD, 24));

        btnKiosk.setBounds(280,310, 400, 100);
        btnKiosk.setFont(new Font("Arial", Font.BOLD, 24));

        btnMain.setBounds(280,440, 400, 100);
        btnMain.setFont(new Font("Arial", Font.BOLD, 24));

        add(jPanel);

        jPanel.add(btnCard);
        jPanel.add(btnKiosk);
        jPanel.add(btnMain);

        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnCard.equals(e.getSource())){
            toCard.switchScreen();
        } else if(btnKiosk.equals(e.getSource())){
            toStore.switchScreen();
        } else if(btnMain.equals(e.getSource())){
            toUser.switchScreen();
        }
    }
}
