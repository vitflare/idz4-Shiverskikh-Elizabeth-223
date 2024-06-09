package com.idz4.ordersservice.repositories;

import com.idz4.ordersservice.core.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findFirstByStatus(Long status);
}
