package com.example.TravelAgency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFormat extends RuntimeException {
    public InvalidFormat(String message) {
        super(message);
    }
}
