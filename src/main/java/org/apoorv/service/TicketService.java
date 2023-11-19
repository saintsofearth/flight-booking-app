package org.apoorv.service;

import com.apoorv.flightTicketBooking.jooq.sample.model.Tables;
import org.apoorv.model.*;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    DSLContext dslContext;

    public TicketModel confirmBookingStatus (BookingModel booking) {
        FlightModel flightDetails = dslContext.selectFrom(Tables.FLIGHT).where(Tables.FLIGHT.FLIGHT_ID.eq(booking.getFlightId())).fetchOne().into(FlightModel.class);
        PassengerModel passengerDetails = dslContext.selectFrom(Tables.PASSENGER).where(Tables.PASSENGER.PASSENGER_ID.eq(booking.getPassengerId())).fetchOne().into(PassengerModel.class);
        AirlineModel airlineDetails = dslContext.selectFrom(Tables.AIRLINE)
                .where(Tables.AIRLINE.AIRLINE_ID.eq(flightDetails.getAirlineId()))
                .fetchOne()
                .into(AirlineModel.class);
        Integer ticketId =  dslContext.insertInto(Tables.TICKET)
                .columns(Tables.TICKET.PASSENGER_ID, Tables.TICKET.TRANSACTION_ID, Tables.TICKET.AIRLINE_ID, Tables.TICKET.FLIGHT_ID)
                .values(booking.getPassengerId(), 1, airlineDetails.getAirlineId(), booking.getFlightId())
                .returningResult(Tables.TICKET.TICKET_ID)
                .fetchOne()
                .into(Integer.class);
        TicketModel ticket = new TicketModel();
        if (ticketId != 0) {
            ticket.setTicketId(ticketId);
            ticket.setFlight(flightDetails);
            ticket.setPassenger(passengerDetails);
            ticket.setAirline(airlineDetails);
            ticket.setTransactionStatus("CONFIRMED");
        }
        return ticket;
    }
}
