package kiosk.service.menu;

public interface InsertMenuService {
    void insertCategory(String storeId, String name);
    void insertNoDiscountMenu(String categoryId, String name, int price);
    void insertTimePercentageDiscountMenu(String categoryId, String name, int price);
    void insertAlwaysCashDiscountMenu();

}
