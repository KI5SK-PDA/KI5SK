package kiosk.service.store;

import kiosk.service.store.dto.req.LaunchStoreRequest;

public interface AdminStoreService {
    void launchStore(final LaunchStoreRequest launchStoreRequest);
}
