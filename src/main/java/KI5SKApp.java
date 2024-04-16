import kiosk.controller.AdminKioskController;
import kiosk.service.menu.dto.req.InsertMenuRequest;
import kiosk.service.store.dto.req.LaunchStoreRequest;
import view.controller.MainViewController;

import java.util.Arrays;
import java.util.List;

public class KI5SKApp {
    private final static AdminKioskController adminKioskController = AdminKioskController.newInstance();

    public static void main(String[] args) {
        initializeData();
        MainViewController.getInstance().run();
    }

    public static void initializeData() {
        initStore();
        initMenu();
    }

    public static void initStore() {
        List<String> storeNames = Arrays.asList("쿵후 마라탕 성수점", "BBQ 성수뚝섬점", "제주국수");
        List<String> imagePaths = Arrays.asList("store/maratang.png", "store/bbq.jpeg", "store/jeju.jpg");

        for (int j=0;j< storeNames.size();j++) {
            adminKioskController.launchStore(LaunchStoreRequest.builder()
                .name(storeNames.get(j))
                .balance(100000)
                .imagePath(imagePaths.get(j))
                .build());
        }
    }

    public static void initMenu() {
        initMaratangMenu();
    }

    private static void initMaratangMenu() {
        // 마라탕집
        String singleCategoryId = adminKioskController.insertCategory("000", "단품");
        String setCategoryId = adminKioskController.insertCategory("000", "세트");
        String sideCategoryId = adminKioskController.insertCategory("000", "사이드");
        String beverageId = adminKioskController.insertCategory("000", "음료");

        // 싱글 메뉴
        String maratangMenuId = adminKioskController.insertAlwaysCashDiscountMenu(singleCategoryId, InsertMenuRequest.builder()
                .name("마라탕(할인 이벤트)")
                .originalPrice(8000)
            .build());
        String addMeetMaratangId = adminKioskController.insertOptionGroup(maratangMenuId, "고기 추가");
        adminKioskController.insertOptionMenu(maratangMenuId, addMeetMaratangId, "양고기 추가 (100g)", 3000);
        adminKioskController.insertOptionMenu(maratangMenuId, addMeetMaratangId, "소고기 추가 (100g)", 3000);
        adminKioskController.insertOptionMenu(maratangMenuId, addMeetMaratangId, "돼지고기 추가 (100g)", 3000);

        String marashanguaMenuId = adminKioskController.insertNoDiscountMenu(singleCategoryId, InsertMenuRequest.builder()
            .name("마라샹궈")
            .originalPrice(9000)
            .build());
        String maralungshaMenuId = adminKioskController.insertNoDiscountMenu(singleCategoryId, InsertMenuRequest.builder()
            .name("마라룽샤")
            .originalPrice(11000)
            .build());

        // 세트 메뉴
        String maratangSetId = adminKioskController.insertNoDiscountMenu(setCategoryId, InsertMenuRequest.builder()
            .name("마라탕 + 콜라")
            .originalPrice(9000)
            .build());
        String marashanguaSetId = adminKioskController.insertNoDiscountMenu(setCategoryId, InsertMenuRequest.builder()
            .name("마라샹궈 + 콜라")
            .originalPrice(10000)
            .build());
        String maralungshaSetId = adminKioskController.insertNoDiscountMenu(setCategoryId, InsertMenuRequest.builder()
            .name("마라룽샤 + 콜라")
            .originalPrice(12000)
            .build());

        // 사이드
        String dumplingId = adminKioskController.insertNoDiscountMenu(sideCategoryId, InsertMenuRequest.builder()
            .name("군만두")
            .originalPrice(4000)
            .build());
        String gguabarow = adminKioskController.insertNoDiscountMenu(sideCategoryId, InsertMenuRequest.builder()
            .name("꿔바로우")
            .originalPrice(24000)
            .build());

        // 음료
        adminKioskController.insertNoDiscountMenu(beverageId, InsertMenuRequest.builder()
            .name("콜라")
            .originalPrice(2000)
            .build());
        adminKioskController.insertNoDiscountMenu(beverageId, InsertMenuRequest.builder()
            .name("제로콜라")
            .originalPrice(2000)
            .build());
        adminKioskController.insertNoDiscountMenu(beverageId, InsertMenuRequest.builder()
            .name("사이다")
            .originalPrice(2000)
            .build());
        adminKioskController.insertNoDiscountMenu(beverageId, InsertMenuRequest.builder()
            .name("환타")
            .originalPrice(2000)
            .build());
        adminKioskController.insertNoDiscountMenu(beverageId, InsertMenuRequest.builder()
            .name("참이슬")
            .originalPrice(5000)
            .build());
        adminKioskController.insertNoDiscountMenu(beverageId, InsertMenuRequest.builder()
            .name("카스")
            .originalPrice(5000)
            .build());
    }
}
