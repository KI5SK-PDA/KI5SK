package kiosk.service.menu;

import kiosk.model.store.Store;
import kiosk.model.store.StoreId;
import kiosk.model.store.StoreRepository;
import kiosk.service.menu.dto.res.CategoryInfo;

import java.util.List;

public class MenuService implements GetMenuService {
    private final StoreRepository storeRepository;

    private MenuService() {
        this.storeRepository = StoreRepository.getInstance();
    }

    public static MenuService newInstance() {
        return new MenuService();
    }

    @Override
    public List<CategoryInfo> getAllMenusByStoreId(String storeId) {
        Store store = storeRepository.findById(StoreId.of(storeId)).orElseThrow(
            () -> new RuntimeException("Store not found"));

        return List.of();
    }
}
