package shoppingbasket.model;


import common.vo.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SelectedMenu {
    private String menuId;
    private String name;
    private int quantity;
    private Money originalPrice;
    private Optional<Money> discountPrice;
    private List<SelectedOption> selectedOptions ;

    @Builder
    private SelectedMenu(String menuId, String name, Money originalPrice, Optional<Money> discountPrice, List<SelectedOption> selectedOptions) {
        this.menuId = menuId;
        this.name = name;
        this.quantity = 1;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        if (selectedOptions != null) {
            this.selectedOptions = selectedOptions;
        } else {
            this.selectedOptions = new ArrayList<>();
        }

    }

    public void addOption(SelectedOption selectedOption) {
        this.selectedOptions.add(selectedOption);
    }

    public void removeOption(String selectedOptionId) {
        int index = -1;
        for (int i=0; i<this.selectedOptions.size(); i++) {
            if (this.selectedOptions.get(i).getOptionId().equals(selectedOptionId)) {
                index = i;
            }
        }

        if (index >= 0)
            this.selectedOptions.remove(index);
    }

    public void plusQuantity() {
        this.quantity += 1;
    }

    public void minusQuantity() {
        this.quantity -= 1;
    }
}
