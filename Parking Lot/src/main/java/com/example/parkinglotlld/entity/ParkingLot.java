package com.example.parkinglotlld.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    private int id;
    private String name;
    private List<ParkingFloor> floors;
    private List<EntryPoint> entryPoints;
    private List<ExitPoint> exitPoints;
}
