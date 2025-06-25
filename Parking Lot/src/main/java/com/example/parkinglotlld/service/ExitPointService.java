package com.example.parkinglotlld.service;

import com.example.parkinglotlld.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExitPointService {
    PaymentService paymentService;
    public boolean exitVehicle(Ticket ticket) {
        if(paymentService.processPayment(ticket)) {
           ticket.getSlot().markAvailable();
           ticket.setExitTime(LocalDateTime.now().toString());
              System.out.println(String.format("Vehicle with number %s exited at %s. Amount paid: %.2f",
                    ticket.getVehicleNumber(), ticket.getExitTime(), ticket.getAmount()));
        }else throw new IllegalArgumentException("Parking lot with id " + ticket.getVehicleNumber() + " does not exist.");
        return false;
    }
}
