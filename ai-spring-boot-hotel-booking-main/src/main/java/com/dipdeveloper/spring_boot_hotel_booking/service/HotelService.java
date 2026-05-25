package com.dipdeveloper.spring_boot_hotel_booking.service;

import com.dipdeveloper.spring_boot_hotel_booking.dto.HotelDTO;
import java.util.List;

/**
 * HotelService - Interface for hotel business operations
 * Provides CRUD operations and business logic for hotels
 */
public interface HotelService {

    /**
     * Create a new hotel
     * @param hotelDTO the hotel data
     * @return the created hotel as DTO
     */
    HotelDTO createHotel(HotelDTO hotelDTO);

    /**
     * Get hotel by ID
     * @param id the hotel ID
     * @return the hotel as DTO
     */
    HotelDTO getHotelById(Long id);

    /**
     * Get all hotels
     * @return list of all hotels as DTOs
     */
    List<HotelDTO> getAllHotels();

    /**
     * Get hotels by location
     * @param location the hotel location
     * @return list of hotels in the location as DTOs
     */
    List<HotelDTO> getHotelsByLocation(String location);

    /**
     * Update an existing hotel
     * @param id the hotel ID
     * @param hotelDTO the updated hotel data
     * @return the updated hotel as DTO
     */
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);

    /**
     * Delete a hotel by ID
     * @param id the hotel ID
     */
    void deleteHotel(Long id);

    /**
     * Get hotel by name
     * @param name the hotel name
     * @return the hotel as DTO
     */
    HotelDTO getHotelByName(String name);
}

