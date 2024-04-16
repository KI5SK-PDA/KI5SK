package card.controller;

import card.service.PurchaseService;
import card.service.PurchaseServiceImpl;
import card.service.dto.PurchaseDTO;
import card.service.dto.res.PurchaseResponse;
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
    private final PurchaseServiceImpl purchaseService;

    public CardController() {
        this.cardService = CardServiceImpl.getInstance();
        this.purchaseService = PurchaseServiceImpl.getInstance();
        new CardTest(this);
    }
    // 카드 추가

    public PurchaseResponse purchase(PurchaseDTO purchaseDTO) {
        return purchaseService.purchase(purchaseDTO);

    }
}
