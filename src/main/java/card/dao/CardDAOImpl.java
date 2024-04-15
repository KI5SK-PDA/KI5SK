package card.dao;

import card.model.Card;
import card.model.Purchase;
import common.vo.Money;

import java.util.HashMap;
import java.util.List;

public class CardDAOImpl implements CardDAO{

    private HashMap<String, Card> cards;
    private HashMap<String, Purchase> puchases;

    public CardDAOImpl(){
        cards = new HashMap<>();
        puchases = new HashMap<>();
    }

    @Override
    public void insertCard(Card card) {

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
    public void chargeCard(String cno, int amount) {

    }

    @Override
    public void deleteCard(String cno) {

    }

    @Override
    public Purchase purchase(String cno, String cpw, String store, Money money) {
        return null;
    }
}
