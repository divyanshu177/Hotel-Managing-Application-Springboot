package com.dipdeveloper.spring_boot_hotel_booking.repository;

import com.dipdeveloper.spring_boot_hotel_booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * RoomRepository
 * Provides database operations for Room entity
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Find all rooms for a specific hotel
     * @param hotelId the hotel ID
     * @return list of rooms in the hotel
     */
    List<Room> findByHotelId(Long hotelId);

    /**
     * Find a specific room by room number and hotel
     * @param roomNumber the room number
     * @param hotelId the hotel ID
     * @return Optional containing the room if found
     */
    Optional<Room> findByRoomNumberAndHotelId(String roomNumber, Long hotelId);

    /**
     * Find rooms within a price range for a hotel
     * @param hotelId the hotel ID
     * @param maxPrice the maximum price per night
     * @return list of rooms within the price range
     */
    List<Room> findByHotelIdAndPriceLessThanEqual(Long hotelId, Double maxPrice);
}


