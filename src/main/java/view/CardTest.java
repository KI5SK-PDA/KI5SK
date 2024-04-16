package view;

import card.controller.CardController;
import card.service.CardServiceImpl;
import common.vo.Money;

public class CardTest {
    CardController cardController;
    public CardTest(CardController cardController){
        this.cardController = cardController;
    }

    public CardController getCardController() {
        return cardController;
    }

    public void test(){
        String cno;
        String cpw;
        String store;
        Money money;
        cno = "134456";
        cpw = "password";
        store= "홍콩반점";
        money = Money.of(0);
        cardController = getCardController();
        cardController.purchase(cno, cpw, store, money);
    }
}
