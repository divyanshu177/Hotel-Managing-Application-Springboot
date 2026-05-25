package com.dipdeveloper.spring_boot_hotel_booking.controller;

import com.dipdeveloper.spring_boot_hotel_booking.dto.HotelDTO;
import com.dipdeveloper.spring_boot_hotel_booking.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * HotelController - REST API endpoints for hotel operations
 * Provides basic CRUD operations for hotels
 */
@RestController
@RequestMapping("/api/v1/hotels")
@Tag(name = "Hotel Management", description = "APIs for managing hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * Create a new hotel
     * @param hotelDTO the hotel data
     * @return the created hotel
     */
    @PostMapping
    @Operation(summary = "Create a new hotel", description = "Creates a new hotel with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Hotel created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    /**
     * Get all hotels
     * @return list of all hotels
     */
    @GetMapping
    @Operation(summary = "Get all hotels", description = "Retrieves a list of all hotels")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotels retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    /**
     * Get hotel by ID
     * @param id the hotel ID
     * @return the hotel
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get hotel by ID", description = "Retrieves a specific hotel by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel found and returned"),
        @ApiResponse(responseCode = "404", description = "Hotel not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<HotelDTO> getHotelById(@Parameter(description = "Hotel ID") @PathVariable Long id) {
        HotelDTO hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }
}

