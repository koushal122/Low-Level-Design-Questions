package com.example.parkinglotlld.design.strategies;

import com.example.parkinglotlld.entity.ParkingFloor;
import com.example.parkinglotlld.entity.ParkingSlot;
import com.example.parkinglotlld.enums.VehicleTypes;

import java.util.Optional;

public interface SlotAllocationStrategy {
    Optional<ParkingSlot> allocateSlot(ParkingFloor floor, VehicleTypes type);
}
