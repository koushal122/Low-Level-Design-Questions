package com.example.parkinglotlld.entity;

import com.example.parkinglotlld.design.observers.FloorObserver;
import com.example.parkinglotlld.enums.VehicleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ParkingFloor {
    private int id;
    private List<ParkingSlot> parkingSlots;
    private List<FloorObserver> observers;

    public ParkingFloor() {
        this.parkingSlots = new ArrayList<>();
        this.observers = new ArrayList<>();
    }


    public void addAllSlots(List<VehicleTypes> vehicleTypes) {
        for(VehicleTypes types : vehicleTypes){
            switch (types){
                case CAR ->  addSlot(new CarSlot(this));
                case BIKE -> addSlot(new BikeSlot(this));
                case EV_CAR -> addSlot(new EVCarSlot(this));
                case EV_BIKE -> addSlot(new EVBikeSlot(this));
                default -> throw new IllegalArgumentException("Unsupported vehicle type: " + types);
            }
        }
    }

    public void addSlot(ParkingSlot slot) {
        parkingSlots.add(slot);
        notifyObservers();
    }

    public void removeSlot(ParkingSlot slot) {
        parkingSlots.remove(slot);
        notifyObservers();
    }

    public void freeSlot(ParkingSlot slot) {
        slot.markAvailable();
        notifyObservers();
    }

    public void occupySlot(ParkingSlot slot) {
        slot.markOccupied();
        notifyObservers();
    }

    public void notifyObservers() {
        observers
                .forEach(observer -> observer.onSlotChange(getAvailableSlots()));
    }

    private Map<VehicleTypes, Long> getAvailableSlots() {
       return parkingSlots.stream()
                .filter(ParkingSlot::isAvailable)
                .collect(Collectors.groupingBy(ParkingSlot::getVehicleType, Collectors.counting()));
    }

    public void addObserver(FloorObserver observer) {
        observers.add(observer);
    }
}
