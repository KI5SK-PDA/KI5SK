package shoppingbasket.service;

import shoppingbasket.service.dto.req.AddSelectedMenuRequest;
import shoppingbasket.service.dto.req.AddSelectedOptionRequest;
import shoppingbasket.service.dto.res.SelectedMenuResponse;

import java.util.List;

public interface ShoppingBasketService {
    void addSelectedMenu(AddSelectedMenuRequest request);
    void addOptions(AddSelectedOptionRequest request);
    void clear();
    void removeSelectedMenu(String menuId);
    List<SelectedMenuResponse> getAllSelectedMenus();

    void incrementQuantityById(String menuId);
    void decrementQuantityById(String menuId);
    void removeSelectedOption(String menuId, String optionId);
}
