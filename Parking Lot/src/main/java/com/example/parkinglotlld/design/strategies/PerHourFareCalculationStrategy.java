package com.example.parkinglotlld.design.strategies;

import com.example.parkinglotlld.entity.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class PerHourFareCalculationStrategy implements FareCalculationStrategies{
    @Override
    public double calculateFare(Ticket ticket) {
        if (ticket == null || ticket.getEntryTime() == null) {
            throw new IllegalArgumentException("Ticket or time information is invalid");
        }
        long durationInHour = Math.max(1,Duration.between(LocalDateTime.parse(ticket.getEntryTime()),LocalDateTime.now()).toHours());
        double fare = durationInHour * ticket.getSlot().getBaseFare();
        if(ticket.isChargingNeeded()){
            //in real world instead of hardcoding we can have a config file
            // or we can calculate based on usage hours
            fare+=50;
        }
        System.out.println("fare was " + fare);
        return fare;
    }
}
