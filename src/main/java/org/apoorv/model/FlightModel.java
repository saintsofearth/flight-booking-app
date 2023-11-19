package org.apoorv.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FlightModel {
    private Integer airlineId;
    private String start;
    private String destination;
    private String layover;
    private LocalDate dateOfJourney;
    private LocalTime durationOfJourney;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer discountId;
    private Float price;
}
