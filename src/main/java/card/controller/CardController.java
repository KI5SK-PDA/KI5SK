package card.controller;

import card.vo.Card;
import card.vo.Company;
import common.vo.Money;

import card.service.CardServiceImpl;

import java.util.List;

public class CardController {
    // 1. 카드 추가 & 2. 카드 조회 & 3. 카드 충전 & 4. 카드 삭제 & 5. 카드 충전
    private final CardServiceImpl cardService;

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    // 카드 추가
    public Card insertCard(String uid, String companyName, String pw){
        return cardService.insertCard(uid, pw, companyName);
    }

    public List<Card> findCardsByUser(String uid) {
        return cardService.findCardsByUser(uid);
    }

    public Card findCardByCno(String cno){
        return cardService.findCardByCno(cno);
    }

    

}
