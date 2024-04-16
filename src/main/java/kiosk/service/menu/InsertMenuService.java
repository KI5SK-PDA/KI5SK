package kiosk.service.menu;

public interface InsertMenuService {
    void insertCategory(String storeId, String name);
    void insertBreakfastDiscountMenu(String categoryId, String name, int price);
    void insertEventDiscountMenu(String categoryId, String name, int price);

}
