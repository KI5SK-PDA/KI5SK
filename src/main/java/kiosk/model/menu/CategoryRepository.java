package kiosk.model.menu;

import kiosk.model.Repository;
import kiosk.model.store.Store;
import kiosk.model.store.StoreId;

import java.util.*;

public class CategoryRepository implements Repository<CategoryId, Category> {
    private Map<CategoryId, Category> categoryMap;
    private int idOffset;

    private CategoryRepository(){
        categoryMap = new HashMap<>();
        idOffset = 0;
    }

    //싱글톤
    private static class CategoryRepositoryHolder {
        private static final CategoryRepository INSTANCE = new CategoryRepository();
    }

    public static CategoryRepository getInstance() {
        return CategoryRepository.CategoryRepositoryHolder.INSTANCE;
    }

    @Override
    public Optional<Category> findById(CategoryId categoryId) {
        return Optional.ofNullable(categoryMap.getOrDefault(categoryId, null));
    }

    public Category create(Category category, StoreId storeId) {
        CategoryId newId = CategoryId.of(storeId, idOffset++);
        category.setId(newId);
        categoryMap.put(newId, category);

        return category;
    }

    @Override
    public Category save(Category category) {
        if (category.getId() == null)
            throw new IllegalArgumentException("Need Category ID: This");

        categoryMap.put(category.getId(), category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    @Override
    public void deleteById(CategoryId categoryId) {
        categoryMap.remove(categoryId);
    }

    @Override
    public void clear() {
        categoryMap.clear();
    }
}
