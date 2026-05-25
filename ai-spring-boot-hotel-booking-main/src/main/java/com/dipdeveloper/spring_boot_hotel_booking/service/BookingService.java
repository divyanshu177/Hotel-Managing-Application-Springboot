package com.dipdeveloper.spring_boot_hotel_booking.service;

import com.dipdeveloper.spring_boot_hotel_booking.dto.BookingDTO;
import java.time.LocalDate;
import java.util.List;

/**
 * BookingService - Interface for booking business operations
 * Provides CRUD operations and business logic for bookings
 */
public interface BookingService {

    /**
     * Create a new booking
     * @param bookingDTO the booking data
     * @return the created booking as DTO
     */
    BookingDTO createBooking(BookingDTO bookingDTO);

    /**
     * Get booking by ID
     * @param id the booking ID
     * @return the booking as DTO
     */
    BookingDTO getBookingById(Long id);

    /**
     * Get all bookings
     * @return list of all bookings as DTOs
     */
    List<BookingDTO> getAllBookings();

    /**
     * Get all bookings for a specific room
     * @param roomId the room ID
     * @return list of bookings for the room as DTOs
     */
    List<BookingDTO> getBookingsByRoomId(Long roomId);

    /**
     * Get all bookings for a specific guest
     * @param guestName the guest name
     * @return list of bookings for the guest as DTOs
     */
    List<BookingDTO> getBookingsByGuestName(String guestName);

    /**
     * Check for conflicting bookings (overlapping dates)
     * @param roomId the room ID
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return list of conflicting bookings as DTOs
     */
    List<BookingDTO> getConflictingBookings(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    /**
     * Update an existing booking
     * @param id the booking ID
     * @param bookingDTO the updated booking data
     * @return the updated booking as DTO
     */
    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);

    /**
     * Delete a booking by ID
     * @param id the booking ID
     */
    void deleteBooking(Long id);
}

