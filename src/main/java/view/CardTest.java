package view;

import card.controller.CardController;
import card.service.CardServiceImpl;
import card.vo.Card;
import common.vo.Money;
import user.model.User;

import java.util.List;
import java.util.Scanner;

public class CardTest {
    public static void main(String[] args) {
        CardTest cardTest = new CardTest(new CardController());
        Scanner sc = new Scanner(System.in);
        cardTest.test();

    }
    CardController cardController;
    public CardTest(CardController cardController){
        this.cardController = cardController;
    }

    public CardController getCardController() {
        return cardController;
    }

    public void test(){
        User user = new User("user01","userPW","테스트이름");

        System.out.println("=======INSERT CARD======");
        Card card1 = cardController.insertCard("우리은행","uri1234");
        Card card2 = cardController.insertCard("신한은행","sh1234");
        System.out.println(card1.toString());
        System.out.println(card2.toString());

        System.out.println("=======findCardByCno=======");
        Card findCard = cardController.findCardByCno(card1.getCno());
        System.out.println(findCard.toString());

        System.out.println("=========chargeCard========");
        Card chargeCard = cardController.chargeCard(card1.getCno(),"500000");
        System.out.println(chargeCard);

        System.out.println("=====Purchase=========");

    }

}
