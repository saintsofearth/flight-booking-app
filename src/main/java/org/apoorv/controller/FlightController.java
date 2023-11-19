package org.apoorv.controller;

import com.apoorv.flightTicketBooking.jooq.sample.model.tables.pojos.Flight;
import org.apoorv.model.BookingModel;
import org.apoorv.model.ErrorModel;
import org.apoorv.model.FlightModel;
import org.apoorv.model.PassengerModel;
import org.apoorv.service.FlightService;
import org.apoorv.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("flight")
@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PassengerController passengerController;

    @GetMapping("search")
    public List<Flight> getFlight (@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("date") String dateOfJourney) {
        return flightService.searchFlight(from, to, dateOfJourney);
    }

    @PostMapping("add")
    public ResponseEntity<?> addFlight(@RequestBody FlightModel flight) {
        int flightStatus = flightService.createFlight(flight);
        if (flightStatus == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("{flightId}/updateDiscount/{discountId}")
    public ResponseEntity<?> updateFlightDiscount(@PathVariable("flightId") Integer flightId, @PathVariable("discountId") Integer discountId) {
        int updateStatus = flightService.updateDiscount(flightId, discountId);
        if (updateStatus == 1) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("{flightId}/register-passenger")
    public ResponseEntity<?> registerPassenger (@PathVariable("flightId") Integer flightId, @RequestBody PassengerModel passenger) {
        PassengerModel registeredPassenger = passengerController.addPassenger(passenger).getBody();
        BookingModel bookingStatus = flightService.registerPassengerForFlight(flightId, registeredPassenger.getPassengerId());
        if (bookingStatus.getFlightId() == flightId && bookingStatus.getPassengerId() == registeredPassenger.getPassengerId()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingStatus);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorModel("Unable to register at the moment"));
    }
}
