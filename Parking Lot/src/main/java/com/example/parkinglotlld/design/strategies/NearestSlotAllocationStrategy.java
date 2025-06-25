package com.example.parkinglotlld.design.strategies;

import com.example.parkinglotlld.entity.ParkingFloor;
import com.example.parkinglotlld.entity.ParkingSlot;
import com.example.parkinglotlld.enums.VehicleTypes;

import java.util.Optional;

public class NearestSlotAllocationStrategy implements SlotAllocationStrategy{
    @Override
    public Optional<ParkingSlot> allocateSlot(ParkingFloor floor, VehicleTypes type) {
        return floor.getParkingSlots().stream()
                .filter(slot->slot.isAvailable()&&slot.getVehicleType().equals(type))
                .findFirst();
    }
}
