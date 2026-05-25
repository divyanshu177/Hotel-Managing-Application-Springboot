package com.dipdeveloper.spring_boot_hotel_booking.repository;

import com.dipdeveloper.spring_boot_hotel_booking.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * HotelRepository
 * Provides database operations for Hotel entity
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    /**
     * Find hotel by name
     * @param name the hotel name
     * @return Optional containing the hotel if found
     */
    Optional<Hotel> findByName(String name);

    /**
     * Find all hotels in a specific location
     * @param location the hotel location
     * @return list of hotels in the location
     */
    List<Hotel> findByLocation(String location);
}


