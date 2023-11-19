package org.apoorv.model;

import lombok.Data;

@Data
public class ErrorModel {
    private String message;

    public ErrorModel(String message) {
        this.message = message;
    }
}
