package card.interService.dto.req;

import card.vo.Purchase;
import common.vo.Money;

import java.util.Date;

public class PurchaseRequest {

    private String cno; // 카드 번호
    private String cpw; // 카드 비밀번호
    private Money money; // 결제 금액
    private Date date;
    private String store; // 상점 이름

    public PurchaseRequest(String cno, String cpw, Money money, Date date, String store) {
        this.cno = cno;
        this.cpw = cpw;
        this.money = money;
        this.date = date;
        this.store = store;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCpw() {
        return cpw;
    }

    public void setCpw(String cpw) {
        this.cpw = cpw;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "cno='" + cno + '\'' +
                ", cpw='" + cpw + '\'' +
                ", money=" + money +
                ", date=" + date +
                ", store='" + store + '\'' +
                '}';
    }
}
