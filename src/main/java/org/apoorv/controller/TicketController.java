package org.apoorv.controller;

import org.apoorv.model.BookingModel;
import org.apoorv.model.TicketModel;
import org.apoorv.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ticket")
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("check-in")
    public ResponseEntity<?> generateTicket (@RequestBody BookingModel booking) {
        TicketModel ticket =  ticketService.confirmBookingStatus(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }
}
