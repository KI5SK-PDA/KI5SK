package card.controller;

import card.service.PurchaseService;
import card.service.PurchaseServiceImpl;
import card.service.dto.PurchaseDTO;
import card.service.dto.res.PurchaseResponse;
import card.vo.Card;
import card.vo.Company;
import card.vo.Purchase;
import common.vo.Money;
import view.card.CardFrame;


import card.service.CardServiceImpl;
import view.CardTest;
import java.util.Date;
import java.util.List;

public class CardController {
    // 1. 카드 추가 & 2. 카드 조회 & 3. 카드 충전 & 4. 카드 삭제 & 5. 카드 충전
    private final CardServiceImpl cardService;
    private final PurchaseServiceImpl purchaseService;

    public CardController() {
        this.cardService = CardServiceImpl.getInstance();
        this.purchaseService = PurchaseServiceImpl.getInstance();
        new CardTest(this);
    }

    public void runView(){
        new CardFrame(this);
    }

    // 카드 추가
    public Card insertCard(String companyName, String pw){
        return cardService.insertCard("kkh", pw, companyName);
    }

    public List<Card> findCardsByUser() {
        return cardService.findCardsByUser("kkh");
    }

    // 결제
    public PurchaseResponse purchase(PurchaseDTO purchaseDTO) {
        return purchaseService.purchase(purchaseDTO);

    }
    // 카드 조회 - cno
    public Card findCardByCno(String cno){
        return cardService.findCardByCno(cno);
    }

    // 카드 충전
    public Card chargeCard(String cno, int amount){
        return cardService.chargeCard(cno, amount);
    }
    // 카드 삭제
    public Card deleteCard(String cno) {
        return cardService.deleteCard(cno);
    }
    // 구매내역 조회
    public List<Purchase> findPurchasesByCno(String cno){
        return cardService.findPurchasesByCno(cno);
    }

    public List<Purchase> findPurchasesByCno(String cno){
        return cardService.findPurchasesByCno(cno);
    }

}
