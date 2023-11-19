package org.apoorv.service;

import com.apoorv.flightTicketBooking.jooq.sample.model.Tables;
import com.apoorv.flightTicketBooking.jooq.sample.model.tables.pojos.Passenger;
import org.apoorv.model.PassengerModel;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    DSLContext dslContext;

    public PassengerModel insertPassenger(PassengerModel passenger) {
         return dslContext.insertInto(Tables.PASSENGER)
                 .columns(Tables.PASSENGER.FNAME, Tables.PASSENGER.LNAME, Tables.PASSENGER.CONTACT_NUMBER, Tables.PASSENGER.DATE_OF_BIRTH, Tables.PASSENGER.USER_PROFILE_ID)
                 .values(passenger.getFname(), passenger.getLname(), passenger.getContactNumber(), passenger.getDateOfBirth(), passenger.getUserProfileId())
                 .returningResult(Tables.PASSENGER.PASSENGER_ID, Tables.PASSENGER.FNAME, Tables.PASSENGER.LNAME, Tables.PASSENGER.CONTACT_NUMBER, Tables.PASSENGER.DATE_OF_BIRTH, Tables.PASSENGER.USER_PROFILE_ID)
                 .fetchOne()
                 .into(PassengerModel.class);
    }

    public List<Integer> getPassengerByContactNumber (PassengerModel passenger)  {
        return dslContext.select(Tables.PASSENGER.PASSENGER_ID)
                .from(Tables.PASSENGER)
                .where(Tables.PASSENGER.CONTACT_NUMBER.eq(passenger.getContactNumber()))
                .fetchInto(Integer.class);

    }
}
