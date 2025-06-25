package com.example.parkinglotlld.design.factories;

import com.example.parkinglotlld.entity.*;
import com.example.parkinglotlld.enums.VehicleTypes;

public class ParkingSlotFactory {
    public static ParkingSlot getParkingSlot(ParkingFloor parkingFloor,VehicleTypes vehicleType) {
        if (vehicleType == VehicleTypes.CAR) {
            return new CarSlot(parkingFloor);
        } else if (vehicleType == VehicleTypes.BIKE) {
            return new BikeSlot(parkingFloor);
        } else if (vehicleType == VehicleTypes.EV_CAR) {
            return new EVCarSlot(parkingFloor);
        } else if (vehicleType == VehicleTypes.EV_BIKE) {
            return new EVBikeSlot(parkingFloor);
        } else {
            throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }
    }
}
