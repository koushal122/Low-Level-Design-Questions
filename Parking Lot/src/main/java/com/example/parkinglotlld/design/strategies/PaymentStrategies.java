package com.example.parkinglotlld.design.strategies;

import com.example.parkinglotlld.entity.ParkingSlot;
import com.example.parkinglotlld.entity.Ticket;

public interface PaymentStrategies {
    boolean pay(Ticket ticket,double amount);
}
