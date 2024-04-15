import kiosk.controller.AdminKioskController;
import kiosk.service.store.dto.req.LaunchStoreRequest;
import view.store.StoreFrame;

import java.util.Arrays;
import java.util.List;

public class KI5SKApp {
    private final static AdminKioskController adminKioskController = AdminKioskController.newInstance();

    public static void main(String[] args) {
        initializeData();
        new StoreFrame();
    }

    public static void initializeData() {
        initStore();
    }

    public static void initStore() {
        List<String> storeNames = Arrays.asList("쿵후 마라탕 성수점", "BBQ 성수뚝섬점", "제주국수");
        List<String> imagePaths = Arrays.asList("store/maratang.png", "store/bbq.jpeg", "store/jeju.jpg");
        for (int i=0; i<10;i++) {
            for (int j=0;j< storeNames.size();j++) {
                adminKioskController.launchStore(LaunchStoreRequest.builder()
                    .name(storeNames.get(j))
                    .balance(100000)
                    .imagePath(imagePaths.get(j))
                    .build());
            }
        }

    }
}
