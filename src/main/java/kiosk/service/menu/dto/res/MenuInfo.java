package kiosk.service.menu.dto.res;

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
public class MenuInfo {
    private String id;
    private String name;
    private int originalPrice;
    private Optional<Integer> discountPrice;
    private Optional<String> discountInfo;


}
