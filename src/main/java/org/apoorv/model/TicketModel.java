package org.apoorv.model;

import lombok.Data;

@Data
public class TicketModel {
    private Integer ticketId;
    private PassengerModel passenger;
    private String transactionStatus;
    private FlightModel flight;
    private AirlineModel airline;
}
