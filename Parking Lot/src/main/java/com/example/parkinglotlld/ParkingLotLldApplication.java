package com.example.parkinglotlld;

import com.example.parkinglotlld.design.observers.DisplayBoard;
import com.example.parkinglotlld.design.observers.FloorObserver;
import com.example.parkinglotlld.design.strategies.NearestSlotAllocationStrategy;
import com.example.parkinglotlld.design.strategies.PerHourFareCalculationStrategy;
import com.example.parkinglotlld.design.strategies.UpiPaymentStrategy;
import com.example.parkinglotlld.entity.*;
import com.example.parkinglotlld.enums.VehicleTypes;
import com.example.parkinglotlld.service.EntryPointService;
import com.example.parkinglotlld.service.ExitPointService;
import com.example.parkinglotlld.service.PaymentService;
import com.example.parkinglotlld.service.TicketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@SpringBootApplication
public class ParkingLotLldApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ParkingLotLldApplication.class, args);
        ParkingFloor groundFlor= new ParkingFloor();
        ParkingFloor firstFloor= new ParkingFloor();


        //let's create display observer
        FloorObserver groundFloorDisplayBoard = new DisplayBoard();
        FloorObserver firstFloorDisplayBoard = new DisplayBoard();
        groundFlor.addObserver(groundFloorDisplayBoard);
        firstFloor.addObserver(firstFloorDisplayBoard);


        groundFlor.addAllSlots(List.of(VehicleTypes.CAR,VehicleTypes.BIKE,VehicleTypes.EV_BIKE));
        firstFloor.addAllSlots(List.of(VehicleTypes.EV_CAR,VehicleTypes.EV_CAR,VehicleTypes.EV_CAR));
        //here for now i have passed empty lists for entry and exit points
        //because here we are storing multiple entry and exit points for simplicity
        //if you want then you can list<EntryPoint> in entry point service class and store in map
        //and store same information in ticket.
        ParkingLot parkingLot = new ParkingLot(1, "Central Park", List.of(groundFlor, firstFloor), new ArrayList<>(), new ArrayList<>());
        TicketService ticketService=new TicketService();
        EntryPointService entryPointService=new EntryPointService(parkingLot,ticketService,new NearestSlotAllocationStrategy());
        //here we are passing UPI payment strategy and per hour fare calculation strategy directly to payment service
        //but in real world for dynamic payment strategy we can use factory pattern and same for fare calculation strategy
        //factory pattern means we store all this information in ticket itself and while calculating fare we dynamically fetch strategy and that will calculate the fare.
        ExitPointService exitPointService=new ExitPointService(new PaymentService(new UpiPaymentStrategy(), new PerHourFareCalculationStrategy()));


        groundFloorDisplayBoard.printCurrentSlots();
        firstFloorDisplayBoard.printCurrentSlots();

        //let's park the vehicle.
        Ticket ticket=entryPointService.parkVehicle(VehicleTypes.CAR, "KA-01-1234",false);
        System.out.println("Ticket generated: " + ticket);

        groundFloorDisplayBoard.printCurrentSlots();

        //let's park another vehicle
        Ticket ticket2=entryPointService.parkVehicle(VehicleTypes.EV_BIKE, "KA-01-1234",true);
        System.out.println("Ticket generated: " + ticket);

        // unpark the first vehicle
        if(exitPointService.exitVehicle(ticket)){
            System.out.println("Vehicle unparked Successfully");
        }


    }

}
