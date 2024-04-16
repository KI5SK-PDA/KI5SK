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




}
