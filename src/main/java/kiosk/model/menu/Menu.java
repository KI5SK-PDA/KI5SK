package kiosk.model.menu;

import common.vo.Money;
import kiosk.model.discounter.Discounter;

import java.util.List;
import java.util.Optional;

public class Menu {

    private int offsetId;
    private Optional<Discounter> discounter;
    private String name;
    private Money originalPrice;

    private List<OptionGroupMenu> optionGroups;

    public String getName() {
        return name;
    }

    public Money getOriginalPrice() {
        return originalPrice;
    }

    public Money getDiscountPrice(){
        if(discounter.isPresent()){
            return discounter.get().getDiscountPrice(this.originalPrice);
        }else{
            return null;
        }
    }

    public String getDiscountInfo(){
        if(discounter.isPresent()){
            return discounter.get().getDiscountInfo();
        }else{
            return "할인 정보가 없습니다.";
        }
    }

    public void updateOptionGroups(OptionGroupMenu optionGroupMenu){
        optionGroups.add(optionGroupMenu);
    }

    public List<OptionGroupMenu> getAllOptionMenus(){
        return optionGroups;
    }
}
