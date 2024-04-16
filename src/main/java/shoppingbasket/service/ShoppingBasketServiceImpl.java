package shoppingbasket.service;

import common.vo.Money;
import shoppingbasket.model.SelectedMenu;
import shoppingbasket.model.SelectedMenuRepository;
import shoppingbasket.service.dto.req.AddSelectedMenuRequest;
import shoppingbasket.service.dto.res.SelectedMenuResponse;
import shoppingbasket.service.dto.res.SelectedOptionResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShoppingBasketServiceImpl implements ShoppingBasketService {
    private final SelectedMenuRepository selectedMenuRepository = SelectedMenuRepository.getInstance();


    @Override
    public void addSelectedMenu(final AddSelectedMenuRequest request) {
        selectedMenuRepository.add(SelectedMenu.builder()
                .menuId(request.getMenuId())
                .name(request.getName())
                .originalPrice(Money.of(request.getOriginalPrice()))
                .discountPrice(Optional.ofNullable(
                    request.getDiscountPrice().isEmpty()?null:Money.of(request.getDiscountPrice().get())))
            .build());
    }

    @Override
    public void addOptions(AddSelectedMenuRequest request) {

    }

    @Override
    public void clear() {
        selectedMenuRepository.clear();
    }

    @Override
    public void removeSelectedMenu(String menuId) {
        selectedMenuRepository.deleteById(menuId);
    }

    @Override
    public List<SelectedMenuResponse> getAllSelectedMenus() {
        return selectedMenuRepository.findAll().stream().map(selectedMenu -> {
            return SelectedMenuResponse.builder()
                .menuId(selectedMenu.getMenuId())
                .name(selectedMenu.getName())
                .quantity(selectedMenu.getQuantity())
                .originalPrice(selectedMenu.getOriginalPrice().toInt())
                .discountPrice(Optional.ofNullable(
                    selectedMenu.getDiscountPrice().isEmpty()?null:selectedMenu.getDiscountPrice().get().toInt()))
                .selectedOptions(selectedMenu.getSelectedOptions().stream()
                    .map(selectedOption -> SelectedOptionResponse.builder()
                        .price(selectedOption.getPrice())
                        .name(selectedOption.getName())
                        .optionId(selectedOption.getOptionId())
                        .build())
                    .collect(Collectors.toList()))
                .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void incrementQuantityById(String menuId) {
        SelectedMenu menu = selectedMenuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.plusQuantity();
    }

    @Override
    public void decrementQuantityById(String menuId) {
        SelectedMenu menu = selectedMenuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.minusQuantity();
    }
}
