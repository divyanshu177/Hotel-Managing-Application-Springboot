package com.dipdeveloper.spring_boot_hotel_booking.exception;

public class RoomNotAvailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RoomNotAvailableException(String message) {
        super(message);
    }

    public RoomNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}


