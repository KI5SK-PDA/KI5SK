package card.service;


import card.model.CardDAO;
import card.model.CardDAOImpl;
import card.vo.Card;
import card.vo.Company;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CardServiceImpl implements CardService{

    private final CardDAOImpl cardDAO;

    private CardServiceImpl(){
        this.cardDAO = CardDAOImpl.getInstance();
    }
    public static CardServiceImpl getInstance(){
        return new CardServiceImpl();
    }

    @Override
    public Card insertCard(String uid, String cpw, String companyName) {
        if("".equals(companyName)){
            System.out.println("은행 선택이 필요");
            return null;
        } else if("".equals(cpw)){
            System.out.println("비밀번호 입력 필요");
            return null;
        }
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
    public Card chargeCard(String cno, String amount) {
        try{
            if(cno != null ){
                return cardDAO.chargeCard(cno, Money.of(Integer.parseInt(amount)));
            }
            return null;
        } catch(Error error){
            System.out.println("카드 충전 과정 중 에러 : "+error);
            return null;
        }
    }

    @Override
    public Card deleteCard(String cno) {
        if(cno != null){
            return cardDAO.deleteCard(cno);
        }
        return null;
    }


    public List<Purchase> findPurchasesByCno(String cno){
        if(cno != null){
            return cardDAO.findPurchasesByCno(cno);
        }
        return null;
    }

    public List<Purchase> findPurchasesByCno(String cno){
        if(cno != null){
            return cardDAO.findPurchasesByCno(cno);
        }
        return null;
    }

    public String newCardNo(){
        UUID uuid = UUID.randomUUID();
        return changeCardFormat(uuid.toString().replaceAll("\\-",""));
    }

    public String changeCardFormat(String uuid){
        StringBuilder formattedCardNo = new StringBuilder();
        formattedCardNo.append(uuid.substring(0,4)).append("-")
                        .append(uuid.substring(4,8)).append("-")
                        .append(uuid.substring(8,12)).append("-")
                        .append(uuid.substring(12,16));
        return formattedCardNo.toString();
    }

}
