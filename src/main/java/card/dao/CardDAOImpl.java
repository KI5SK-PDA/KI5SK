package card.dao;

import card.model.Card;
import card.model.Purchase;
import model.vo.Money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDAOImpl implements CardDAO{

    private HashMap<String, Card> cards;
    private HashMap<String, Purchase> puchases;

    public CardDAOImpl(){
        cards = new HashMap<>();
        puchases = new HashMap<>();
    }

    @Override
    public Card insertCard(Card card) {
        if(card != null){
            cards.put(card.getCno(), card);
            return card;
        }
        return null;
    }

    @Override
    public Card findCardByCno(String cno) {
        if(cno == null) return null;
        return cards.getOrDefault(cno, null);
    }

    @Override
    public List<Card> findCardsByUser(String uid) {
        if(uid == null) return null;
        List<Card> userCards = new ArrayList<>();
        for(Map.Entry<String, Card> entry : cards.entrySet()){
            if(uid.equals(entry.getValue().getUid())){
                userCards.add(entry.getValue());
            }
        }
        return userCards;
    }

    @Override
    public void chargeCard(String cno, int amount) {
        if(cno != null){
            cards.get(cno).getMoney().add(Money.of(amount));
        }
    }

    @Override
    public void deleteCard(String cno) {
        if(cno != null){
            cards.remove(cno);
        }
    }

    @Override
    public Purchase purchase(String cno, String cpw, String store, Money money) {
        return null;
    }
}
