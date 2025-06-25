package com.example.parkinglotlld.design.strategies;

import com.example.parkinglotlld.entity.Ticket;

public class UpiPaymentStrategy implements PaymentStrategies{
    @Override
    public boolean pay(Ticket ticket,double amout) {
        System.out.println("Payment done via UPI for ticket: " + ticket.getTicketId());
        ticket.setAmount(amout);
        return true;
    }
}
