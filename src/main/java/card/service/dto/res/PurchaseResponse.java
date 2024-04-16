package card.service.dto.res;

import card.service.dto.PurchaseDTO;

public class PurchaseResponse {
    private boolean success;
    private String message;
    private PurchaseDTO purchaseDTO;
    public PurchaseResponse(){};
    public PurchaseResponse(boolean success, String message, PurchaseDTO purchaseDTO) {
        this.success = success;
        this.message = message;
        this.purchaseDTO = purchaseDTO;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PurchaseDTO getPurchaseDTO() {
        return purchaseDTO;
    }

    public void setPurchaseDTO(PurchaseDTO purchaseDTO) {
        this.purchaseDTO = purchaseDTO;
    }
}
