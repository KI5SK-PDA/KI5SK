package card.interService;

import card.interService.dto.req.PurchaseRequest;
import card.interService.dto.res.PurchaseResponse;

public interface PurchaseInterService {
    PurchaseResponse purchase(PurchaseRequest purchaseRequest);
}
