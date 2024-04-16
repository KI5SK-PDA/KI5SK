package card.service;

import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;
import java.util.List;

public interface CardService {
    Card insertCard(Card card);
    Card findCardByCno(String cno);
    List<Card> findCardsByUser(String uid);
    Card chargeCard(String cno, int amount);
    void deleteCard(String cno);



    // 결제
    boolean authenticateCard(String cno, String cpw);

    boolean canPurchase(String cno, Money money);

    Purchase purchase(String cno, String cpw, Date date, String store, Money money);
}
