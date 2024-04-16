package kiosk.controller;

import kiosk.service.menu.InsertMenuService;
import kiosk.service.menu.MenuService;
import kiosk.service.menu.dto.req.InsertMenuRequest;
import kiosk.service.store.AdminStoreService;
import kiosk.service.store.StoreService;
import kiosk.service.store.dto.req.LaunchStoreRequest;

public class AdminKioskController implements AdminStoreService, InsertMenuService {
    private final AdminStoreService storeService;
    private final InsertMenuService insertMenuService;

    private AdminKioskController() {
        this.storeService = StoreService.newInstance();
        this.insertMenuService = MenuService.newInstance();
    }

    public static AdminKioskController newInstance() {
        return new AdminKioskController();
    }

    @Override
    public void launchStore(LaunchStoreRequest launchStoreRequest) {
        storeService.launchStore(launchStoreRequest);
    }

    @Override
    public String insertCategory(String storeId, String name) {
        return insertMenuService.insertCategory(storeId, name);
    }

    @Override
    public String insertNoDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest) {
        return insertMenuService.insertNoDiscountMenu(categoryId, insertMenuRequest);
    }

    @Override
    public String insertTimePercentageDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest) {
        return insertMenuService.insertTimePercentageDiscountMenu(categoryId, insertMenuRequest);
    }

    @Override
    public String insertAlwaysCashDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest) {
        return insertMenuService.insertAlwaysCashDiscountMenu(categoryId, insertMenuRequest);
    }

    @Override
    public String insertOptionGroup(String menuId, String name) {
        return insertMenuService.insertOptionGroup(menuId, name);
    }

    @Override
    public String insertOptionMenu(String menuId, String optionGroupId, String name, int price) {
        return insertMenuService.insertOptionMenu(menuId, optionGroupId, name, price);
    }
}
