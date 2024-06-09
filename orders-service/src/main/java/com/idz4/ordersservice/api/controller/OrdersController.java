package com.idz4.ordersservice.api.controller;

import com.idz4.ordersservice.api.requests.CreateOrderRequest;
import com.idz4.ordersservice.core.entities.Orders;
import com.idz4.ordersservice.core.interfaces.IOrdersService;
import com.idz4.ordersservice.core.interfaces.IStationsService;
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
    @Autowired
    private IStationsService stationsService;

    @GetMapping("/getinfo")
    public ResponseEntity<Orders> getInfo(@RequestParam Long id) {
        Orders order = ordersService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/getallstations")
    public ResponseEntity<?> getAllStations() {
        return ResponseEntity.status(HttpStatus.OK).body(stationsService.getAllStations());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest requestBody) {
        if (isBlank(requestBody.getStation_from()) || isBlank(requestBody.getStation_to())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("All fields must be filled");
        }
        var departureStation = stationsService.getByStationName(requestBody.getStation_from());
        var arrivalStation = stationsService.getByStationName(requestBody.getStation_to());
        if (departureStation== null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departure station not found");
        }
        if (arrivalStation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arrival station not found");
        }
        var user_id = request.getAttribute("user_id");
        Orders order = new Orders();
        order.setUser_id(Long.parseLong(user_id.toString()));
        order.setStation_from_id(departureStation.getId());
        order.setStation_to_id(arrivalStation.getId());
        order.setStatus(1L);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.createOrder(order));
    }

    private boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}
