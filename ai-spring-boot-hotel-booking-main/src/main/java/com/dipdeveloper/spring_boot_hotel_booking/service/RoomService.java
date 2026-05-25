package com.dipdeveloper.spring_boot_hotel_booking.service;

import com.dipdeveloper.spring_boot_hotel_booking.dto.RoomDTO;
import java.util.List;

/**
 * RoomService - Interface for room business operations
 * Provides CRUD operations and business logic for rooms
 */
public interface RoomService {

    /**
     * Create a new room for a hotel
     * @param hotelId the hotel ID
     * @param roomDTO the room data
     * @return the created room as DTO
     */
    RoomDTO createRoom(Long hotelId, RoomDTO roomDTO);

    /**
     * Get room by ID
     * @param id the room ID
     * @return the room as DTO
     */
    RoomDTO getRoomById(Long id);

    /**
     * Get all rooms
     * @return list of all rooms as DTOs
     */
    List<RoomDTO> getAllRooms();

    /**
     * Get all rooms for a specific hotel
     * @param hotelId the hotel ID
     * @return list of rooms in the hotel as DTOs
     */
    List<RoomDTO> getRoomsByHotelId(Long hotelId);

    /**
     * Get a specific room by room number and hotel
     * @param roomNumber the room number
     * @param hotelId the hotel ID
     * @return the room as DTO
     */
    RoomDTO getRoomByRoomNumberAndHotelId(String roomNumber, Long hotelId);

    /**
     * Get rooms within a price range for a hotel
     * @param hotelId the hotel ID
     * @param maxPrice the maximum price per night
     * @return list of rooms within the price range as DTOs
     */
    List<RoomDTO> getRoomsWithinPrice(Long hotelId, Double maxPrice);

    /**
     * Update an existing room
     * @param id the room ID
     * @param roomDTO the updated room data
     * @return the updated room as DTO
     */
    RoomDTO updateRoom(Long id, RoomDTO roomDTO);

    /**
     * Delete a room by ID
     * @param id the room ID
     */
    void deleteRoom(Long id);
}

