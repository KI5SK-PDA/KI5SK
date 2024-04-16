package card.service;

import card.service.dto.PurchaseDTO;
import card.service.dto.req.PurchaseRequest;
import card.service.dto.res.PurchaseResponse;
import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;

public interface PurchaseService {
    PurchaseResponse purchase(PurchaseRequest purchaseRequest);
}
