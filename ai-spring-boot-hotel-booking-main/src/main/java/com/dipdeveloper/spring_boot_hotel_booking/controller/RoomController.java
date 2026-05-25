package com.dipdeveloper.spring_boot_hotel_booking.controller;

import com.dipdeveloper.spring_boot_hotel_booking.dto.RoomDTO;
import com.dipdeveloper.spring_boot_hotel_booking.service.RoomService;
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
 * RoomController - REST API endpoints for room operations
 * Provides basic CRUD operations for rooms
 */
@RestController
@RequestMapping("/api/v1/rooms")
@Tag(name = "Room Management", description = "APIs for managing hotel rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Create a new room for a hotel
     * @param hotelId the hotel ID
     * @param roomDTO the room data
     * @return the created room
     */
    @PostMapping
    @Operation(summary = "Create a new room", description = "Creates a new room for the specified hotel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Room created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Hotel not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomDTO> createRoom(
            @Parameter(description = "Hotel ID where the room will be created") @RequestParam Long hotelId,
            @Valid @RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = roomService.createRoom(hotelId, roomDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    /**
     * Get all rooms
     * @return list of all rooms
     */
    @GetMapping
    @Operation(summary = "Get all rooms", description = "Retrieves a list of all rooms across all hotels")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rooms retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}

