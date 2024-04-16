package kiosk.service.menu;

import kiosk.service.menu.dto.req.InsertMenuRequest;
import kiosk.service.menu.dto.res.OptionMenuInfo;

import java.util.List;

public interface InsertMenuService {
    String insertCategory(String storeId, String name);
    String insertNoDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest);
    String insertTimePercentageDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest);
    String insertAlwaysCashDiscountMenu(String categoryId, InsertMenuRequest insertMenuRequest);

    String insertOptionGroup(String menuId, String name);

    String insertOptionMenu(String menuId, String optionGroupId, String name, int price);
}

/*
* 1. 새로운 카테고리 만들기
* 2. 새로운 메뉴 만들기
*   2-1. 카테고리 선택
*   2-2. 옵션 그룹 만들기
*   2-3. 옵션 메뉴 만들기
* */