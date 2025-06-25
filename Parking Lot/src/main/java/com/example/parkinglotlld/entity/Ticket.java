package com.example.parkinglotlld.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String ticketId;
    private String vehicleNumber;
    private String vehicleType;
    private String entryTime;
    private String exitTime;
    private double amount;
    private ParkingSlot slot;
    private boolean chargingNeeded;
}
