package ru.akhilko.hotelsdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exceptions {
    ADDRESS_NOT_FOUND("Address cound't be found", 404),
    COULDNT_CREATE_HOTEL("Couldn't create Hotel", 400);

    private final String message;
    private final int statusCode;
}
