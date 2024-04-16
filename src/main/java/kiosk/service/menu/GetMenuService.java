package kiosk.service.menu;

import kiosk.service.menu.dto.res.CategoryInfo;

import java.util.List;

public interface GetMenuService {
    List<CategoryInfo> getAllMenusByStoreId(String storeId);
}
