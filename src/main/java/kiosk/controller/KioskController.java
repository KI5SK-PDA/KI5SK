package kiosk.controller;

import kiosk.service.menu.GetMenuService;
import kiosk.service.menu.MenuService;
import kiosk.service.menu.dto.res.CategoryInfo;
import kiosk.service.order.OrderService;
import kiosk.service.order.OrderServiceImpl;
import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;
import kiosk.service.store.CommonStoreService;
import kiosk.service.store.StoreService;
import kiosk.service.store.dto.res.StoreResponse;

import java.util.List;

public class KioskController implements CommonStoreService, GetMenuService, OrderService {
    private final CommonStoreService storeService;
    private final GetMenuService menuService;
    private final OrderService orderService;

    private KioskController() {
        this.storeService = StoreService.newInstance();
        this.menuService = MenuService.newInstance();
        this.orderService = OrderServiceImpl.newInstance();
    }

    public static KioskController newInstance() {
        return new KioskController();
    }

    @Override
    public List<StoreResponse> getAllStore() {
        return storeService.getAllStore();
    }

    @Override
    public StoreResponse getStoreById(String id) {
        return storeService.getStoreById(id);
    }

    @Override
    public List<CategoryInfo> getAllMenusByStoreId(String storeId) {
        return menuService.getAllMenusByStoreId(storeId);
    }

    @Override
    public OrderResponse orderMenus(OrderRequest orderRequest) {
        return orderService.orderMenus(orderRequest);
    }
}
