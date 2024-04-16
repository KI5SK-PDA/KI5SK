package card.model;

import card.service.dto.PurchaseDTO;
import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;
import java.util.List;

public interface CardDAO {
    Card insertCard(Card card);
    Card findCardByCno(String cno);
    List<Card> findCardsByUser(String uid);
    Card chargeCard(String cno, Money money);
    Card deleteCard(String cno);
    List<Purchase> findPurchasesByCno(String cno);
    boolean authenticateCard(String cno, String cpw);
    boolean canPurchase(String cno, Money money);
    PurchaseDTO purchase(String cno, String cpw, Date date, String store, Money money);
}
