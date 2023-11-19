package org.apoorv.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassengerModel {
    private Integer passengerId;
    private String fname;
    private String lname;
    private String contactNumber;
    private LocalDate dateOfBirth;
    private Integer userProfileId;
}
