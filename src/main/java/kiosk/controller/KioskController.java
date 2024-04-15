package kiosk.controller;

import kiosk.service.store.CommonStoreService;
import kiosk.service.store.StoreService;
import kiosk.service.store.dto.res.StoreResponse;

import java.util.List;

public class KioskController implements CommonStoreService {
    private final CommonStoreService storeService;

    private KioskController() {
        this.storeService = StoreService.newInstance();
    }

    public static KioskController newInstance() {
        return new KioskController();
    }

    @Override
    public List<StoreResponse> getAllStore() {
        return storeService.getAllStore();
    }
}
