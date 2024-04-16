package card.service;

import card.service.dto.PurchaseDTO;
import card.service.dto.res.PurchaseResponse;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;

public interface PurchaseService {
    // 결제
//    boolean authenticateCard(String cno, String cpw);
//
//    boolean canPurchase(String cno, Money money);

    PurchaseResponse purchase(PurchaseDTO purchaseDTO);
}
