package card.service;

import card.model.CardDAOImpl;
import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;
import java.util.List;

public class CardServiceImpl implements CardService{
    private final CardDAOImpl cardDAO;

    private CardServiceImpl(){
        this.cardDAO = CardDAOImpl.getInstance();
    }
    public static CardServiceImpl getInstance(){
        return new CardServiceImpl();
    }

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


}
