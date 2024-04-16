package shoppingbasket.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectedMenuRepository {
    private Map<String, SelectedMenu> selectedMenus = new HashMap<>();

    private static class SelectedMenuRepositoryHolder {
        private static final SelectedMenuRepository INSTANCE = new SelectedMenuRepository();
    }

    public static SelectedMenuRepository getInstance() {
        return SelectedMenuRepositoryHolder.INSTANCE;
    }

    public void add(SelectedMenu selectedMenu) {
        selectedMenus.put(selectedMenu.getMenuId(), selectedMenu);
    }

    public Optional<SelectedMenu> findById(String id) {
        if (selectedMenus.containsKey(id)) {
            return Optional.of(selectedMenus.get(id));
        }

        return Optional.empty();
    }

    public void deleteById(String id) {
        selectedMenus.remove(id);
    }

    public List<SelectedMenu> findAll() {
        return new ArrayList<>(selectedMenus.values());
    }

    public void clear() {
        selectedMenus.clear();
    }
}
