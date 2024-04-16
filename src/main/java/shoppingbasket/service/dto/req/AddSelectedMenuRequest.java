package shoppingbasket.service.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AddSelectedMenuRequest {
    private String name;
    private String menuId;
    private int originalPrice;
    private Optional<Integer> discountPrice;
}
