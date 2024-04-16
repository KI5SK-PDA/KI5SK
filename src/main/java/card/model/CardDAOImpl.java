package card.model;

import card.service.dto.PurchaseDTO;
import card.vo.Card;
import card.vo.Purchase;
import common.vo.Money;

import java.util.*;

public class CardDAOImpl implements CardDAO{

    private final HashMap<String, Card> cards;
    private final HashMap<String, Purchase> purchases;

    private final static CardDAOImpl cardDAO = new CardDAOImpl();

    public CardDAOImpl(){
        cards = new HashMap<>();
        purchases = new HashMap<>();
    }

    public static CardDAOImpl getInstance(){
        return cardDAO;
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
    public boolean authenticateCard(String cno, String cpw) {
        Card card = cards.get(cno);
        if(card != null && card.getCpw().equals(cpw)){
            return true;
        }else return false;
    }

    @Override
    public boolean canPurchase(String cno, Money money) {
        Card card = cards.get(cno);
        if(money.toInt() <= card.getMoney().toInt())
            return true;
        else return false;
    }

    @Override
    public PurchaseDTO purchase(String cno, String cpw, Date date, String store, Money money) {
        // pid 생성
        int maxNum = 0;
        for (String existingPid : purchases.keySet()){
            int number = Integer.parseInt(existingPid.substring(1));
            if(number > maxNum) maxNum = number;
        }

        String pid = String.format("P%02d", maxNum+1);

        // 카드 회사 할인율 찾아 가격 할인
        Card card = cards.get(cno);
        double discount = card.getCompany().getDiscount();
        Money discount_money = money.applyRate(discount);

        // 구매내역 객체 생성 후 purchases에 저장
        Purchase newPurchase = new Purchase(pid, cno, date, discount_money, store);
        purchases.put(pid, newPurchase);

        // 카드 잔고 업데이트
        card.setMoney(discount_money);

        return new PurchaseDTO(cno, cpw, money, date, store);
    }

//    @Override
//    public List<Purchase> findPurchasesByCno(String cno){
//        List<Purchase> cnoPurchases = new ArrayList<>();
//        for(Map.Entry<String, Purchase> entry : purchases.entrySet()){
//            if(cno.equals(entry.getValue().getCno())){
//                cnoPurchases.add(entry.getValue());
//            }
//        }
//        return cnoPurchases;
//    }
}
