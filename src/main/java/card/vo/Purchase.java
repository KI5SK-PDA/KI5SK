package card.vo;

import common.vo.Money;

import java.util.Date;

public class Purchase {
    private String pid; // 구매 번호
    private String cno; // 카드 번호
    private Date date; // 결제 날짜
    private Money money; // 결제 금액
    private String store; // 결제 가게 내역

    public Purchase(String pid, String cno, Date date, Money money, String store) {
        this.pid = pid;
        this.cno = cno;
        this.date = date;
        this.money = money;
        this.store = store;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
