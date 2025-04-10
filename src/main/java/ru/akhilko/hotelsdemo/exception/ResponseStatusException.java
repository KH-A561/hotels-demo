package ru.akhilko.hotelsdemo.exception;

import lombok.Getter;

@Getter
public class ResponseStatusException extends Exception {
    public ResponseStatusException(Exceptions exception) {
        super(exception.getMessage());
        this.statusCode = exception.getStatusCode();
    }

    private int statusCode;
}
