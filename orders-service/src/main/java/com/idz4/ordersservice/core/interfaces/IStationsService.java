package com.idz4.ordersservice.core.interfaces;

import com.idz4.ordersservice.core.entities.Orders;
import com.idz4.ordersservice.core.entities.Stations;

import java.util.List;

public interface IStationsService {
    Stations getByStationName(String name);
    List<Stations> getAllStations();
}
