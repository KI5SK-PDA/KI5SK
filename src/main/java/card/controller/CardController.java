package card.controller;

import card.vo.Card;
import card.vo.Company;
import card.vo.Purchase;
import common.vo.Money;

import card.service.CardServiceImpl;
import view.CardTest;

import java.util.Date;

public class CardController {
    // 1. 카드 추가 & 2. 카드 조회 & 3. 카드 충전 & 4. 카드 삭제 & 5. 카드 충전
    private final CardServiceImpl cardService;

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
        new CardTest(this);
    }
    // 카드 추가

    public Purchase purchase(String cno, String cpw, String store, Money money) {

        // 카드 번호와 비밀번호가 맞는지 확인
        if (cardService.authenticateCard(cno, cpw)) {
            // 잔액 확인
            if (cardService.canPurchase(cno, money)){
                return null;
            }
            else {
                // 결제
                Date date = new Date();
                return cardService.purchase(cno, cpw, date ,store, money);
            }
        } else {
            return null;
        }
    }
}
