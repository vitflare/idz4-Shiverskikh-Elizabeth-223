package com.idz4.ordersservice.core.services;

import com.idz4.ordersservice.api.responses.CreateOrderResponse;
import com.idz4.ordersservice.core.entities.Orders;
import com.idz4.ordersservice.core.interfaces.IOrdersService;
import com.idz4.ordersservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService implements IOrdersService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public CreateOrderResponse createOrder(Orders order) {
        var response = new CreateOrderResponse();
        response.setId(orderRepository.save(order).getId());
        return response;
    }
}
