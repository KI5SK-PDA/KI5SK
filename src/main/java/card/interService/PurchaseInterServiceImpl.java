package card.interService;

import card.model.CardDAOImpl;
import card.interService.dto.req.PurchaseRequest;
import card.interService.dto.res.PurchaseResponse;
import common.vo.Money;

import java.util.Date;

public class PurchaseInterServiceImpl implements PurchaseInterService {

    private final CardDAOImpl cardDAO;

    private PurchaseInterServiceImpl(){
        this.cardDAO = CardDAOImpl.getInstance();
    }
    public static PurchaseInterServiceImpl getInstance(){
        return new PurchaseInterServiceImpl();
    }

    @Override
    public PurchaseResponse purchase(PurchaseRequest purchaseRequest) {
        String cno = purchaseRequest.getCno();
        String cpw = purchaseRequest.getCpw();
        Money money = purchaseRequest.getMoney();
        String store = purchaseRequest.getStore();

        PurchaseResponse response = new PurchaseResponse();

        if (cardDAO.authenticateCard(cno, cpw)) {
            if (!cardDAO.canPurchase(cno, money)) {
                response.setSuccess(false);
                response.setMessage("카드 잔고보다 더 많은 금액을 결제할 수 없습니다.");
                return response;
            } else {
                Date date = new Date();
                response.setPurchaseDTO(cardDAO.purchase(cno, cpw, date, store, money));
                response.setSuccess(true);
                response.setMessage("결제가 성공적으로 처리되었습니다.");
                return response;
            }
        } else {
            response.setSuccess(false);
            response.setMessage("카드 번호에 대한 비밀번호가 맞지 않습니다");
            return response;
        }
    }
}