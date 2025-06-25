package com.example.parkinglotlld.service;

import com.example.parkinglotlld.design.strategies.FareCalculationStrategies;
import com.example.parkinglotlld.design.strategies.PaymentStrategies;
import com.example.parkinglotlld.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentService {
    private PaymentStrategies paymentStrategies;
    private FareCalculationStrategies fareCalculationStrategies;

    public boolean processPayment(Ticket ticket) {
        double amount = fareCalculationStrategies.calculateFare(ticket);
        if (paymentStrategies.pay(ticket, amount)) {
            System.out.println("Payment successful for ticket: " + ticket.getTicketId());
            return true;
        } else {
            System.out.println("Payment failed for ticket: " + ticket.getTicketId());
            return false;
        }
    }
}
