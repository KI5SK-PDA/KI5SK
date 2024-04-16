package kiosk.service.menu;


import kiosk.model.Repository;

public class CategoryRepository<CategoryId> implements Repository<CategoryId, Category> {

    public static CategoryRepository getInstance() {
        return CategoryRepositoryHolder.INSTANCE;
    }
}
