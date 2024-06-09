package com.idz4.ordersservice.core.interfaces;

import com.idz4.ordersservice.api.responses.CreateOrderResponse;
import com.idz4.ordersservice.core.entities.Orders;

public interface IOrdersService {
    Orders getOrderById(Long id);
    CreateOrderResponse createOrder(Orders order);
}
