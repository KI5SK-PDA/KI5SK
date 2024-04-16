package kiosk.service.order;

import kiosk.service.order.dto.req.OrderRequest;
import kiosk.service.order.dto.res.OrderResponse;

public interface OrderService {

    OrderResponse OrderMenus(OrderRequest orderRequest);
}
