package com.idz4.ordersservice.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stations {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_seq_gen")
    @SequenceGenerator(name = "station_seq_gen", sequenceName = "stations_id_seq", allocationSize = 1)
    private Long id;

    private String station;
}
