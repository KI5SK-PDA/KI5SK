package card.service;

import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.List;

public class CardServiceImpl implements CardService{
    @Override
    public Card insertCard(Card card) {
        return null;
    }

    @Override
    public Card findCardByCno(String cno) {
        return null;
    }

    @Override
    public List<Card> findCardsByUser(String uid) {
        return null;
    }

    @Override
    public Card chargeCard(String cno, int amount) {
        return null;
    }

    @Override
    public void deleteCard(String cno) {

    }

    @Override
    public Purchase purchase(String cno, String cpw, String store, Money money) {
        return null;
    }
}
