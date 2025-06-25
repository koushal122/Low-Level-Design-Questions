package com.example.parkinglotlld.service;

import com.example.parkinglotlld.entity.ParkingSlot;
import com.example.parkinglotlld.entity.Ticket;
import com.example.parkinglotlld.enums.VehicleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class TicketService {
    //In an interview you can tell that we can use a database or in-memory storage instead of using a simple
    //and also we can separate this map to repository layer.
    Map<String,Ticket> ticketMap;
    public TicketService(){
        ticketMap=new HashMap<>();
    }
    public Ticket createTicket(ParkingSlot slot, String vehicleNumber, VehicleTypes vehicleTypes,boolean chargingNeeded) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(generateTicketId(slot.getParkingFloor().getId(),slot.getId()));
        ticket.setEntryTime(LocalDateTime.now().toString());
        ticket.setVehicleType(vehicleTypes.toString());
        ticket.setSlot(slot);
        ticket.setChargingNeeded(chargingNeeded);
        ticket.setVehicleNumber(vehicleNumber);
        ticketMap.put(ticket.getTicketId(),ticket);
        return ticket;
    }

    private String generateTicketId(int id, String id1) {
        return id+"-"+id1+"-"+System.currentTimeMillis();
    }
}
