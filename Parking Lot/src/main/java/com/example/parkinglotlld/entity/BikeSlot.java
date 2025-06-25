package com.example.parkinglotlld.entity;

import com.example.parkinglotlld.enums.VehicleTypes;

public class BikeSlot implements  ParkingSlot {
    private String id;
    private ParkingFloor parkingFloor;
    private VehicleTypes vehicleType;
    private boolean available;

    public BikeSlot(ParkingFloor parkingFloor) {
        this.id = generateId(parkingFloor);
        this.parkingFloor=parkingFloor;
        this.vehicleType=VehicleTypes.BIKE;
        this.available=true;
    }

    private String generateId(ParkingFloor parkingFloor) {
        return String.valueOf(parkingFloor.getId())+"-"+parkingFloor.getParkingSlots().size();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public VehicleTypes getVehicleType() {
        return vehicleType;
    }

    @Override
    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    @Override
    public void markOccupied() {
       this.available=false;
        this.parkingFloor.notifyObservers();
    }

    @Override
    public void markAvailable() {
        this.available=true;
        this.parkingFloor.notifyObservers();
    }

    @Override
    public long getBaseFare() {
        // in real-world applications, this could be dynamic or fetched from a configuration
        return 20;
    }
}
