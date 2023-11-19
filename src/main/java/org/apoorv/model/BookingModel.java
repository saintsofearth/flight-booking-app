package org.apoorv.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingModel {
    private Integer flightId;
    private Integer passengerId;
    private LocalDate dateOfJourney;
}
