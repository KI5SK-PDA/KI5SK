package card.dao;

import card.model.Card;
import card.model.Purchase;
import model.vo.Money;

import java.util.List;

public interface CardDAO {
    Card insertCard(Card card);
    Card findCardByCno(String cno);
    List<Card> findCardsByUser(String uid);
    void chargeCard(String cno, int amount);
    void deleteCard(String cno);
    Purchase purchase(String cno, String cpw, String store , Money money);//미정
}
