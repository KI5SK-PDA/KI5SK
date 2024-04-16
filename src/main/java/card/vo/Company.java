package card.vo;

public class Company {
    private String cname; // 회사 이름
    private double discount; // 할인율

    public Company(String cname){
        this.cname = cname;

        // 신한 3%, 우리 5%, 국민 4%, 농협 7%, 하나 7%
        switch (cname){
            case "신한은행":
                this.discount = 0.03;
                break;
            case "우리은행":
                this.discount = 0.05;
                break;
            case "국민은행":
                this.discount = 0.04;
                break;
            case "농협은행":
                this.discount = 0.07;
                break;
            case "하나은행":
                this.discount = 0.05;
                break;
        }
    }

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

    @Override
    public String toString() {
        return "Company{" +
                "cname='" + cname + '\'' +
                ", discount=" + discount +
                '}';
    }
}
