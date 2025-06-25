package com.example.parkinglotlld.service;

import com.example.parkinglotlld.design.strategies.SlotAllocationStrategy;
import com.example.parkinglotlld.entity.*;
import com.example.parkinglotlld.enums.VehicleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
public class EntryPointService {
    private final ParkingLot lot;
    private final TicketService ticketService;
    private SlotAllocationStrategy slotAllocationStrategy;
    // in real world it is created in repository layer
    public Ticket parkVehicle(VehicleTypes vehicleTypes, String VehicleNumber,boolean chargingNeeded) {
        System.out.println("Lot is "+lot.getName());
        System.out.println("floor count is "+lot.getFloors().size());
        ParkingSlot slot=null;
        for(ParkingFloor f:lot.getFloors()){
            Optional<ParkingSlot> floor=slotAllocationStrategy.allocateSlot(f,vehicleTypes);
            if(floor.isPresent()){
                slot=floor.get();
                break;
            }
        }
        if(slot==null){
            throw new RuntimeException("No slot available for vehicle type: " + vehicleTypes);
        }
        else {
            System.out.println("Allocated slot: " + slot.getId() + " for vehicle: " + VehicleNumber);
        }
        Ticket ticket=ticketService.createTicket(slot, VehicleNumber, vehicleTypes,chargingNeeded);
        if(ticket!=null) slot.markOccupied();
        else throw new RuntimeException("Ticket creation failed for vehicle: " + VehicleNumber);
        return ticket;
    }

}
