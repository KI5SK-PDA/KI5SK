package card.model;

public class Company {
    private String cname; // 회사 이름
    private double discount; // 할인율

    public Company(String cname, double discount){
        this.cname = cname;
        this.discount = discount;
    }

    public String getCname() {
        return cname;
    }

    public double getDiscount() {
        return discount;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
