package card.service;

import card.model.CardDAO;
import card.model.CardDAOImpl;
import card.vo.Card;
import card.vo.Company;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;
import java.util.List;

public class CardServiceImpl implements CardService{

    private CardDAO cardDAO;

    public CardServiceImpl(){
        cardDAO = new CardDAOImpl();
    }

    @Override
    public Card insertCard(String uid, String cpw, String companyName) {
        return cardDAO.insertCard(new Card(newCardNo(), uid, cpw, new Company(companyName)));
    }

    @Override
    public Card findCardByCno(String cno) {
        if(cno != null)
            return cardDAO.findCardByCno(cno);
        return null;
    }

    @Override
    public List<Card> findCardsByUser(String uid) {
        return cardDAO.findCardsByUser(uid);
    }

    @Override
    public Card chargeCard(String cno, int amount) {
        if(cno != null){
            return cardDAO.chargeCard(cno, Money.of(amount));
        }
        return null;
    }

    @Override
    public Card deleteCard(String cno) {
        if(cno != null){
            return cardDAO.deleteCard(cno);
        }
        return null;
    }

    @Override
    public Purchase purchase(String cno, String cpw, String store, Money money) {
        return null;
    }

    public String newCardNo(){
        Date date = new Date();
        return changeCardFormat(date.getTime());
    }

    public String changeCardFormat(long date){
        String cardNo = Long.toString(date);
        StringBuilder formattedCardNo = new StringBuilder();
        formattedCardNo.append(cardNo.substring(0,3)).append("-");
        formattedCardNo.append(cardNo.substring(3,7)).append("-");
        formattedCardNo.append(cardNo.substring(7,11)).append("-");
        formattedCardNo.append(cardNo.substring(11));
        return formattedCardNo.toString();
    }
}
