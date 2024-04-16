package kiosk.service.store;

import kiosk.service.store.dto.res.StoreResponse;

import java.util.List;

public interface CommonStoreService {
    List<StoreResponse> getAllStore();
    StoreResponse getStoreById(String id);
}
