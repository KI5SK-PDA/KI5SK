package card.model;

import card.vo.Card;
import card.vo.Purchase;

import common.vo.Money;
import java.util.List;

public interface CardDAO {
    Card insertCard(Card card);
    Card findCardByCno(String cno);
    List<Card> findCardsByUser(String uid);
    Card chargeCard(String cno, Money money);
    Card deleteCard(String cno);
    Purchase purchase(String cno, String cpw, String store , Money money);//미정
}
