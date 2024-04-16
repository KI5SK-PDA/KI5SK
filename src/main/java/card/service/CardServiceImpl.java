package card.service;

import card.model.CardDAOImpl;
import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;
import java.util.List;

public class CardServiceImpl implements CardService{

    private CardDAOImpl cardDAO;
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
    public boolean authenticateCard(String cno, String cpw) {
        return cardDAO.authenticateCard(cno, cpw);
    }

    @Override
    public boolean canPurchase(String cno, Money money) {
        return cardDAO.canPurchase(cno, money);
    }

    @Override
    public Purchase purchase(String cno, String cpw, Date date, String store, Money money){
        return cardDAO.purchase(cno, cpw, date, store, money);
    }

}
