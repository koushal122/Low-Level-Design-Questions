package com.example.parkinglotlld.design.observers;

import com.example.parkinglotlld.enums.VehicleTypes;

import java.util.Map;

public class DisplayBoard implements FloorObserver {
    Map<VehicleTypes, Long> currentSlots;
    @Override
    public void onSlotChange(Map<VehicleTypes, Long> availableSlots) {
        System.out.println("Available slots : "+availableSlots);
        currentSlots= availableSlots;
    }

    @Override
    public void printCurrentSlots() {
        System.out.println(currentSlots);
    }

}
