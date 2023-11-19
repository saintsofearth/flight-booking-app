package org.apoorv.controller;

import com.apoorv.flightTicketBooking.jooq.sample.model.tables.pojos.Passenger;
import org.apoorv.model.PassengerModel;
import org.apoorv.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @PostMapping("generate")
    public ResponseEntity<PassengerModel> addPassenger(@RequestBody PassengerModel passenger) {
        PassengerModel newPassenger = passengerService.insertPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPassenger);
    }
}
