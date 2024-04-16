package shoppingbasket.service.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SelectedMenuResponse {
    private String menuId;
    private String name;
    private int quantity;
    private int originalPrice;
    private Optional<Integer> discountPrice;
    private List<SelectedOptionResponse> selectedOptions;
}
