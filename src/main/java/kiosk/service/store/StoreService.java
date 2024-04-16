package kiosk.service.store;

import common.vo.Money;
import kiosk.model.store.Store;
import kiosk.model.store.StoreRepository;
import kiosk.service.store.dto.req.LaunchStoreRequest;
import kiosk.service.store.dto.res.StoreResponse;

import java.util.List;
import java.util.stream.Collectors;

public class StoreService implements CommonStoreService, AdminStoreService {
    private final StoreRepository storeRepository;

    private StoreService() {
        this.storeRepository = StoreRepository.getInstance();
    }

    public static StoreService newInstance() {
        return new StoreService();
    }

    @Override
    public List<StoreResponse> getAllStore() {
        return storeRepository.findAll().stream().map(store ->  StoreResponse.builder()
                .id(store.getId().toString())
                .name(store.getName())
                .imagePath(store.getImagePath())
            .build())
            .collect(Collectors.toList());
    }

    @Override
    public void launchStore(final LaunchStoreRequest launchStoreRequest) {
        storeRepository.save(Store.create(
            launchStoreRequest.getName(),
            Money.of(launchStoreRequest.getBalance()),
            launchStoreRequest.getImagePath()));
    }
}
