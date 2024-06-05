package com.idz4.ordersservice.api.controller;

import com.idz4.ordersservice.api.requests.CreateOrderRequest;
import com.idz4.ordersservice.core.entities.Orders;
import com.idz4.ordersservice.core.interfaces.IOrdersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/getinfo")
    public ResponseEntity<Orders> getInfo(@RequestParam Long id) {
        Orders order = ordersService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest requestBody) {
        if (requestBody.getStation_from_id() > 8 ||
                requestBody.getStation_from_id() < 1 ||
                requestBody.getStation_to_id() > 8 ||
                requestBody.getStation_to_id() < 1 ||
                requestBody.getStation_from_id().equals(requestBody.getStation_to_id()))  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong parameters");
        }
        var user_id = request.getAttribute("user_id");
        Orders order = new Orders();
        order.setUser_id(Long.parseLong(user_id.toString()));
        order.setStation_from_id(requestBody.getStation_from_id());
        order.setStation_to_id(requestBody.getStation_to_id());
        order.setStatus(1L);
        ordersService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
    }
}
