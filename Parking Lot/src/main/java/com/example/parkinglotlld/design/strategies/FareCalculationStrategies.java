package com.example.parkinglotlld.design.strategies;

import com.example.parkinglotlld.entity.Ticket;

public interface FareCalculationStrategies {
    double calculateFare(Ticket ticket);
}
