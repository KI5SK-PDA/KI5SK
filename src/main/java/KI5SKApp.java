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
        List<String> storeNames = Arrays.asList("📍 쿵후 마라탕 성수점", "📍 BBQ 성수3점", "📍 제주국수");
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
        initBBQMenu();
    }

    private static void initBBQMenu(){
        // BBQ
        String singleCategoryId = adminKioskController.insertCategory("001", "단품");
        String setCategoryId = adminKioskController.insertCategory("001", "세트");
        String sideCategoryId = adminKioskController.insertCategory("001", "사이드");
        String beverageId = adminKioskController.insertCategory("001", "음료");

        String newBBQMenuId = adminKioskController.insertAlwaysCashDiscountMenu(singleCategoryId, InsertMenuRequest.builder()
                .name("🏷️ BBQ(⭐신메뉴 이벤트⭐)")
                .originalPrice(20000)
                .build());

        String newBBQMenusId = adminKioskController.insertOptionGroup(newBBQMenuId, "신메뉴");
        adminKioskController.insertOptionMenu(newBBQMenuId, newBBQMenusId, "🔖 바사칸윙(23,000 -> 22,000)",2000);
        adminKioskController.insertOptionMenu(newBBQMenuId, newBBQMenuId, "🔖 BBQ 양념치킨(22,000 -> 21,500",500);
        adminKioskController.insertOptionMenu(newBBQMenuId,newBBQMenusId, "🔖  황올 반 + BBQ 양념 반(22,000 -> 21000)",1000);

        String goldOliveChicken = adminKioskController.insertNoDiscountMenu(sideCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 황금 올리브 치킨")
                .originalPrice(20000)
                .build());

        String goldOliveChickenleg = adminKioskController.insertNoDiscountMenu(sideCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 황금 올리브 치킨(닭다리)")
                .originalPrice(21000)
                .build());

        String basakGalic = adminKioskController.insertNoDiscountMenu(sideCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 바삭갈릭")
                .originalPrice(21000)
                .build());

        String danzzanGalic = adminKioskController.insertNoDiscountMenu(sideCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 단짠갈릭")
                .originalPrice(23000)
                .build());

        String TwolegSet = adminKioskController.insertNoDiscountMenu(setCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 다리가 두개 더 SET")
                .originalPrice(26000)
                .build());

        String TwoChickenSet = adminKioskController.insertNoDiscountMenu(setCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 두마리 치킨 SET")
                .originalPrice(40500)
                .build());

        String OneHalfSet = adminKioskController.insertNoDiscountMenu(setCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 한마리 반 치킨 SET")
                .originalPrice(33500)
                .build());

    }

    private static void initMaratangMenu() {
        // 마라탕집
        String singleCategoryId = adminKioskController.insertCategory("000", "단품");
        String setCategoryId = adminKioskController.insertCategory("000", "세트");
        String sideCategoryId = adminKioskController.insertCategory("000", "사이드");
        String beverageId = adminKioskController.insertCategory("000", "음료");

        // 싱글 메뉴
        String maratangMenuId = adminKioskController.insertAlwaysCashDiscountMenu(singleCategoryId, InsertMenuRequest.builder()
                .name("🏷️ 마라탕(⭐할인 이벤트⭐)")
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
