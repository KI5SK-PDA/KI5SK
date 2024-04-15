package card.model;

import model.vo.Money;

public class Card {
    private String cno; // 카드 번호
    private String uid; // 카드 소지자
    private String cpw; // 카드 비밀번호
    private Company company; // 카드 회사
    private Money money; // 잔액

    public Card(String cno, String uid, String cpw, Company company, Money money) {
        this.cno = cno;
        this.uid = uid;
        this.cpw = cpw;
        this.company = company;
        this.money = money;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCpw() {
        return cpw;
    }

    public void setCpw(String cpw) {
        this.cpw = cpw;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
