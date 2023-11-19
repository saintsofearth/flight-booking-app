package org.apoorv.service;

import com.apoorv.flightTicketBooking.jooq.sample.model.Tables;
import com.apoorv.flightTicketBooking.jooq.sample.model.tables.pojos.Flight;
import org.apoorv.model.BookingModel;
import org.apoorv.model.FlightModel;
import org.apoorv.model.PassengerModel;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    DSLContext dslContext;

    public List<Flight> searchFlight (String from, String to, String dateOfJourney) {
        return dslContext.selectFrom(Tables.FLIGHT)
                .where(Tables.FLIGHT.START.equalIgnoreCase(from)
                        .and(Tables.FLIGHT.DESTINATION.equalIgnoreCase(to))
                        .and(Tables.FLIGHT.DATE_OF_JOURNEY.eq(LocalDate.parse(dateOfJourney)))).fetchInto(Flight.class);
    }

    public int createFlight (FlightModel flight) {
        return dslContext.insertInto(Tables.FLIGHT)
                .columns(Tables.FLIGHT.AIRLINE_ID,
                        Tables.FLIGHT.START,
                        Tables.FLIGHT.DESTINATION,
                        Tables.FLIGHT.LAYOVER,
                        Tables.FLIGHT.DATE_OF_JOURNEY,
                        Tables.FLIGHT.DURATION_OF_JOURNEY,
                        Tables.FLIGHT.DISCOUNT_ID,
                        Tables.FLIGHT.START_TIME,
                        Tables.FLIGHT.END_TIME,
                        Tables.FLIGHT.PRICE)
                .values(flight.getAirlineId(),
                        flight.getStart(),
                        flight.getDestination(),
                        flight.getLayover(),
                        flight.getDateOfJourney(),
                        flight.getDurationOfJourney(),
                        flight.getDiscountId(),
                        flight.getStartTime(),
                        flight.getEndTime(),
                        flight.getPrice())
                .execute();
    }

    public int updateDiscount (Integer flightId, Integer discountId) {
        return dslContext.update(Tables.FLIGHT)
                .set(Tables.FLIGHT.DISCOUNT_ID, discountId)
                .where(Tables.FLIGHT.FLIGHT_ID.eq(flightId))
                .execute();
    }

    public BookingModel registerPassengerForFlight(Integer flightId, Integer passengerId) {
//        Integer newPassengerId = dslContext.insertInto(Tables.PASSENGER)
//                .columns(Tables.PASSENGER.FNAME, Tables.PASSENGER.LNAME, Tables.PASSENGER.CONTACT_NUMBER, Tables.PASSENGER.DATE_OF_BIRTH, Tables.PASSENGER.USER_PROFILE_ID)
//                .values(passenger.getFname(), passenger.getLname(), passenger.getContactNumber(), passenger.getDateOfBirth(), passenger.getUserProfileId())
//                .returningResult(Tables.PASSENGER.PASSENGER_ID)
//                .fetchOne()
//                .into(Integer.class);
        LocalDate dateOfJourney = dslContext.select(Tables.FLIGHT.DATE_OF_JOURNEY)
                .from(Tables.FLIGHT)
                .where(Tables.FLIGHT.FLIGHT_ID.eq(flightId))
                .fetchOne().into(LocalDate.class);
        return dslContext.insertInto(Tables.PASSENGER_VIEW_FLIGHT)
                .columns(Tables.PASSENGER_VIEW_FLIGHT.FLIGHT_ID, Tables.PASSENGER_VIEW_FLIGHT.PASSENGER_ID,
                        Tables.PASSENGER_VIEW_FLIGHT.DATE_OF_JOURNEY)
                .values(flightId, passengerId, dateOfJourney)
                .returningResult(Tables.PASSENGER_VIEW_FLIGHT.FLIGHT_ID, Tables.PASSENGER_VIEW_FLIGHT.PASSENGER_ID, Tables.PASSENGER_VIEW_FLIGHT.DATE_OF_JOURNEY)
                .fetchOne()
                .into(BookingModel.class);
    }
}
