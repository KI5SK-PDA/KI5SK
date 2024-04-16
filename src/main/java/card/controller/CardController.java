package card.controller;

import card.view.CardView;
import card.vo.Card;
import card.vo.Purchase;

import card.service.CardServiceImpl;

import java.util.List;

public class CardController {
    // 1. 카드 추가 & 2. 카드 조회 & 3. 카드 충전 & 4. 카드 삭제 & 5. 카드 충전
    private final CardServiceImpl cardService;

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    public void runView(){
        new CardView(this);
    }

    // 카드 추가
    public Card insertCard(String companyName, String pw){
        return cardService.insertCard("kkh", pw, companyName);
    }

    public List<Card> findCardsByUser() {
        return cardService.findCardsByUser("kkh");
    }

    public Card findCardByCno(String cno){
        return cardService.findCardByCno(cno);
    }

    public Card chargeCard(String cno, String amount){
        return cardService.chargeCard(cno, amount);
    }

    public Card deleteCard(String cno) {
        return cardService.deleteCard(cno);
    }

    public List<Purchase> findPurchasesByCno(String cno){
        return cardService.findPurchasesByCno(cno);
    }

}
