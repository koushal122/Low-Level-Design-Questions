package com.example.parkinglotlld.service;

import com.example.parkinglotlld.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class ParkingLotService {
    Map<Integer, ParkingLot> parkingLots;
    public ParkingLot createParkingLot(String name, List<EntryPoint> entryPoints, List<ExitPoint> exitPoints, List<ParkingFloor> floors) {
        int id = parkingLots.size() + 1;
        ParkingLot parkingLot = new ParkingLot(id, name,floors,entryPoints,exitPoints);
        parkingLots.put(id, parkingLot);
        return parkingLot;
    }

    public ParkingLot addNewFloor(ParkingFloor floor,int lotId) {
        if(parkingLots.containsKey(lotId)){
            ParkingLot parkingLot = parkingLots.get(lotId);
            parkingLot.getFloors().add(floor);
            return parkingLot;
        } else {
            throw new IllegalArgumentException("Parking lot with id " + lotId + " does not exist.");
        }
    }

    //same can be done for adding entry and exit points
}
