package card.model;

import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDAOImpl implements CardDAO{

    private final HashMap<String, Card> cards;
    private final HashMap<String, Purchase> puchases;

    public CardDAOImpl(){
        cards = new HashMap<>();
        puchases = new HashMap<>();
    }

    @Override
    public Card insertCard(Card card) {
        cards.put(card.getCno(), card);
        return card;
    }

    @Override
    public Card findCardByCno(String cno) {
        return cards.getOrDefault(cno, null);
    }

    @Override
    public List<Card> findCardsByUser(String uid) {
        List<Card> userCards = new ArrayList<>();
        for(Map.Entry<String, Card> entry : cards.entrySet()){
            if(uid.equals(entry.getValue().getUid())){
                userCards.add(entry.getValue());
            }
        }
        return userCards;
    }

    @Override
    public Card chargeCard(String cno, Money money) {
        cards.get(cno).setMoney(cards.get(cno).getMoney().add(money));
        return cards.get(cno);
    }

    @Override
    public Card deleteCard(String cno) {
        return cards.remove(cno);
    }

    @Override
    public Purchase purchase(String cno, String cpw, String store, Money money) {
        return null;
    }

    @Override
    public List<Purchase> findPurchasesByCno(String cno){
        List<Purchase> cnoPurchases = new ArrayList<>();
        for(Map.Entry<String, Purchase> entry : puchases.entrySet()){
            if(cno.equals(entry.getValue().getCno())){
                cnoPurchases.add(entry.getValue());
            }
        }
        return cnoPurchases;
    }
}
