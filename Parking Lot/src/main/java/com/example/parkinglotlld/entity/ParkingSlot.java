package com.example.parkinglotlld.entity;

import com.example.parkinglotlld.enums.VehicleTypes;

public interface ParkingSlot {
    String getId();
    boolean isAvailable();
    VehicleTypes getVehicleType();
    ParkingFloor getParkingFloor();
    void markOccupied();
    void markAvailable();
    long getBaseFare();
}
