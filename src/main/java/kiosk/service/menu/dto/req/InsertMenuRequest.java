package kiosk.service.menu.dto.req;

import common.vo.Money;
import kiosk.model.discounter.Discounter;
import lombok.*;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class InsertMenuRequest {
    private String name;
    private int originalPrice;
}
