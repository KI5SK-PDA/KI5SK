package card.controller;

import card.interService.PurchaseInterServiceImpl;
import card.interService.dto.req.PurchaseRequest;
import card.interService.dto.res.PurchaseResponse;
import card.vo.Card;
import user.model.UserSession;


import card.service.CardServiceImpl;

import java.util.List;

public class CardController {
    // 1. 카드 추가 & 2. 카드 조회 & 3. 카드 충전 & 4. 카드 삭제 & 5. 카드 충전
    private final CardServiceImpl cardService;
    private final PurchaseInterServiceImpl purchaseService;

    public CardController() {
        this.cardService = CardServiceImpl.getInstance();
        this.purchaseService = PurchaseInterServiceImpl.getInstance();
    }

    // 카드 추가
    public Card insertCard(String companyName, String pw){
        return cardService.insertCard(UserSession.getUser().getUid(), pw, companyName);
    }

    public List<Card> findCardsByUser() {
        return cardService.findCardsByUser(UserSession.getUser().getUid());
    }

    // 결제
    public PurchaseResponse purchase(PurchaseRequest purchaseRequest) {
        return purchaseService.purchase(purchaseRequest);

    }
    // 카드 조회 - cno
    public Card findCardByCno(String cno){
        return cardService.findCardByCno(cno);
    }

    // 카드 충전
    public Card chargeCard(String cno, String amount){
        return cardService.chargeCard(cno, amount);
    }
    // 카드 삭제
    public Card deleteCard(String cno) {
        return cardService.deleteCard(cno);
    }
    // 구매내역 조회
//    public List<Purchase> findPurchasesByCno(String cno){
//        return cardService.findPurchasesByCno(cno);
//    }

}