package com.idz4.ordersservice.core.services;

import com.idz4.ordersservice.core.entities.Stations;
import com.idz4.ordersservice.core.interfaces.IStationsService;
import com.idz4.ordersservice.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationsService implements IStationsService {
    @Autowired
    private StationRepository stationRepository;

    @Override
    public Stations getByStationName(String name) {
        return stationRepository.findByStation(name);
    }

    @Override
    public List<Stations> getAllStations() {
        return stationRepository.findAll();
    }
}
