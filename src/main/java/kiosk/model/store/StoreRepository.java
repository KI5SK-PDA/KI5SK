package kiosk.model.store;

import kiosk.model.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StoreRepository implements Repository<StoreId, Store> {
    private Map<StoreId, Store> storeMap;
    private int idOffset;

    private StoreRepository() {
        storeMap = new HashMap<>();
        idOffset = 0;
    }

    private static class StoreRepositoryHolder {
        private static final StoreRepository INSTANCE = new StoreRepository();
    }

    public static StoreRepository getInstance() {
        return StoreRepositoryHolder.INSTANCE;
    }

    @Override
    public Optional<Store> findById(StoreId storeId) {
        return Optional.ofNullable(storeMap.getOrDefault(storeId, null));
    }

    @Override
    public Store save(Store store) {
        if (store.getId() == null) {
            StoreId newId = StoreId.of(idOffset++);
            store.setId(newId);
            storeMap.put(newId, store);
            return store;
        }

        storeMap.put(store.getId(), store);
        return store;
    }

    @Override
    public List<Store> findAll() {
        return new ArrayList<>(storeMap.values());
    }

    @Override
    public void deleteById(StoreId storeId) {
        storeMap.remove(storeId);
    }

    @Override
    public void clear() {
        storeMap.clear();
    }
}
