package com.dipdeveloper.spring_boot_hotel_booking.controller;

import com.dipdeveloper.spring_boot_hotel_booking.dto.BookingDTO;
import com.dipdeveloper.spring_boot_hotel_booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * BookingController - REST API endpoints for booking operations
 * Provides basic CRUD operations for bookings
 */
@RestController
@RequestMapping("/api/v1/bookings")
@Tag(name = "Booking Management", description = "APIs for managing room bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Create a new booking
     * @param bookingDTO the booking data
     * @return the created booking
     */
    @PostMapping
    @Operation(summary = "Create a new booking", description = "Creates a new room booking with conflict detection")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Booking created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data or booking dates"),
        @ApiResponse(responseCode = "404", description = "Room not found"),
        @ApiResponse(responseCode = "409", description = "Room not available for selected dates"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    /**
     * Get all bookings
     * @return list of all bookings
     */
    @GetMapping
    @Operation(summary = "Get all bookings", description = "Retrieves a list of all bookings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
}

