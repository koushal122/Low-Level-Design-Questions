package com.example.parkinglotlld.design.observers;

import com.example.parkinglotlld.enums.VehicleTypes;

import java.util.Map;

public interface FloorObserver {
    void onSlotChange(Map<VehicleTypes, Long> availableSlots);
    void printCurrentSlots();
}
