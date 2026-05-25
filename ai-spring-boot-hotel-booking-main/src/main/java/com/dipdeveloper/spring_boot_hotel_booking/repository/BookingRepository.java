package com.dipdeveloper.spring_boot_hotel_booking.repository;

import com.dipdeveloper.spring_boot_hotel_booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * BookingRepository
 * Provides database operations for Booking entity
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Find all bookings for a specific room
     * @param roomId the room ID
     * @return list of bookings for the room
     */
    List<Booking> findByRoomId(Long roomId);

    /**
     * Find all bookings for a specific guest
     * @param guestName the guest name
     * @return list of bookings for the guest
     */
    List<Booking> findByGuestName(String guestName);

    /**
     * Find bookings that overlap with the given date range for a room
     * This helps detect double bookings
     * @param roomId the room ID
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return list of overlapping bookings
     */
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
           "((b.checkInDate >= :checkInDate AND b.checkInDate < :checkOutDate) OR " +
           "(b.checkOutDate > :checkInDate AND b.checkOutDate <= :checkOutDate) OR " +
           "(b.checkInDate <= :checkInDate AND b.checkOutDate >= :checkOutDate))")
    List<Booking> findConflictingBookings(@Param("roomId") Long roomId,
                                          @Param("checkInDate") LocalDate checkInDate,
                                          @Param("checkOutDate") LocalDate checkOutDate);
}



