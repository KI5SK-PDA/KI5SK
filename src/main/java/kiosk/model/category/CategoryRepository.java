package kiosk.model.category;

import kiosk.model.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CategoryRepository implements Repository<CategoryId, Category> {
    private Map<CategoryId, Category> storeMap;
    private int idOffset;

    @Override
    public Optional<Category> findById(CategoryId categoryId) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void deleteById(CategoryId categoryId) {

    }
}
