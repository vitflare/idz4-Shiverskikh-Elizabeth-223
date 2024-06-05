package com.idz4.ordersservice.core.services;

import com.idz4.ordersservice.core.entities.Orders;
import com.idz4.ordersservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OrderProcessingService {

    @Autowired
    private OrderRepository orderRepository;

    private final Random random = new Random();

    @Scheduled(fixedDelay = 5000)
    public void emulateOrderProcessing() {
        Orders orderToProcess = orderRepository.findFirstByStatus(1L);

        if (orderToProcess != null) {
            long newStatus = random.nextLong(2) + 2;
            orderToProcess.setStatus(newStatus);
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            orderRepository.save(orderToProcess);
        }
    }
}
