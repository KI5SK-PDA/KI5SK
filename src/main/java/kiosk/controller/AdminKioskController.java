package kiosk.controller;

import kiosk.service.store.AdminStoreService;
import kiosk.service.store.StoreService;
import kiosk.service.store.dto.req.LaunchStoreRequest;

public class AdminKioskController implements AdminStoreService {
    private final AdminStoreService storeService;

    private AdminKioskController() {
        this.storeService = StoreService.newInstance();
    }

    public static AdminKioskController newInstance() {
        return new AdminKioskController();
    }

    @Override
    public void launchStore(LaunchStoreRequest launchStoreRequest) {
        storeService.launchStore(launchStoreRequest);
    }
}
