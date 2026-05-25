package com.dipdeveloper.spring_boot_hotel_booking.exception;

public class InvalidBookingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidBookingException(String message) {
        super(message);
    }

    public InvalidBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}


