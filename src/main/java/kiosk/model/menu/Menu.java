package kiosk.model.menu;

import common.vo.Money;
import kiosk.model.discounter.Discounter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Menu {

    private static int idOffset = 0;

    @Setter(AccessLevel.PACKAGE)
    private MenuId id;
    private Optional<Discounter> discounter;
    private String name;
    private Money originalPrice;

    private List<OptionGroupMenu> optionGroups;

    private Menu(String name, Money originalPrice, Optional<Discounter> discounter, CategoryId categoryId) {

        this.id = MenuId.of(categoryId, idOffset++);
        this.name = name;
        this.originalPrice = originalPrice;
        this.discounter = discounter;
        this.optionGroups = new ArrayList<>();

    }

    //가려주는 용도
    public static Menu create(String name, Money originalPrice, Optional<Discounter> discounter, CategoryId categoryId){
        return new Menu(name, originalPrice, discounter, categoryId);
    }

    public Money getDiscountPrice(){
        if(discounter.isPresent()) {
            return discounter.get().getDiscountPrice(this.originalPrice);
        }
            return null;

    }

    public String getDiscountInfo(){
        if(discounter.isPresent()) {
            return discounter.get().getDiscountInfo();
        }

        return null;
    }

    public void updateOptionGroups(OptionGroupMenu optionGroupMenu){
        optionGroups.add(optionGroupMenu);
    }

    public List<OptionGroupMenu> getAllOptionMenus(){
        return optionGroups;
    }
}
