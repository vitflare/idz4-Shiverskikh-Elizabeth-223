package com.idz4.ordersservice.repositories;

import com.idz4.ordersservice.core.entities.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Stations, Long> {
    Stations findByStation(String name);
}
