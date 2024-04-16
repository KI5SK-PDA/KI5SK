package shoppingbasket.controller;

import shoppingbasket.service.ShoppingBasketService;
import shoppingbasket.service.ShoppingBasketServiceImpl;
import shoppingbasket.service.dto.req.AddSelectedMenuRequest;
import shoppingbasket.service.dto.req.AddSelectedOptionRequest;
import shoppingbasket.service.dto.res.SelectedMenuResponse;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketController implements ShoppingBasketService, SelectedMenuSubject {
    private final ShoppingBasketService shoppingBasketService;
    private final List<SelectedMenuObserver> menuObservers = new ArrayList<>();

    public ShoppingBasketController() {
        this.shoppingBasketService = new ShoppingBasketServiceImpl();
    }

    @Override
    public void addSelectedMenu(AddSelectedMenuRequest request) {
        shoppingBasketService.addSelectedMenu(request);
        notifyObserver();
    }

    @Override
    public void addOptions(AddSelectedOptionRequest request) {
        shoppingBasketService.addOptions(request);
        notifyObserver();
    }

    @Override
    public void clear() {
        shoppingBasketService.clear();
        notifyObserver();
    }

    @Override
    public void removeSelectedMenu(String menuId) {
        shoppingBasketService.removeSelectedMenu(menuId);
        notifyObserver();
    }

    @Override
    public List<SelectedMenuResponse> getAllSelectedMenus() {
        return shoppingBasketService.getAllSelectedMenus();
    }

    @Override
    public void incrementQuantityById(String menuId) {
        shoppingBasketService.incrementQuantityById(menuId);
        notifyObserver();
    }

    @Override
    public void decrementQuantityById(String menuId) {
        shoppingBasketService.decrementQuantityById(menuId);
        notifyObserver();
    }

    @Override
    public void removeSelectedOption(String menuId, String optionId) {
        shoppingBasketService.removeSelectedOption(menuId, optionId);
        notifyObserver();
    }

    @Override
    public void registerObserver(SelectedMenuObserver o) {
        this.menuObservers.add(o);
    }

    @Override
    public void removeObserver(SelectedMenuObserver o) {
        this.menuObservers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (SelectedMenuObserver o : menuObservers) {
            o.update();
        }
    }
}
