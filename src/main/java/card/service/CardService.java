package card.service;

import card.vo.Card;
import card.vo.Company;
import card.vo.Purchase;
import common.vo.Money;

import java.util.List;

public interface CardService {
    Card insertCard(String uid, String cpw, String companyName);
    Card findCardByCno(String cno);
    List<Card> findCardsByUser(String uid);
    Card chargeCard(String cno, int amount);
    Card deleteCard(String cno);
    Purchase purchase(String cno, String cpw, String store , Money money);//미정
    List<Purchase> findPurchasesByCno(String cno);
}
