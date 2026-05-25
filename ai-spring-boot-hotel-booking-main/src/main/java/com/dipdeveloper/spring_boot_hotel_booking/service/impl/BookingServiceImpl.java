package com.dipdeveloper.spring_boot_hotel_booking.service.impl;

import com.dipdeveloper.spring_boot_hotel_booking.dto.BookingDTO;
import com.dipdeveloper.spring_boot_hotel_booking.entity.Booking;
import com.dipdeveloper.spring_boot_hotel_booking.entity.Room;
import com.dipdeveloper.spring_boot_hotel_booking.exception.InvalidBookingException;
import com.dipdeveloper.spring_boot_hotel_booking.exception.ResourceNotFoundException;
import com.dipdeveloper.spring_boot_hotel_booking.exception.RoomNotAvailableException;
import com.dipdeveloper.spring_boot_hotel_booking.repository.BookingRepository;
import com.dipdeveloper.spring_boot_hotel_booking.repository.RoomRepository;
import com.dipdeveloper.spring_boot_hotel_booking.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Validate dates
        if (bookingDTO.getCheckInDate().isAfter(bookingDTO.getCheckOutDate())) {
            throw new InvalidBookingException("Check-in date must be before check-out date");
        }

        if (bookingDTO.getCheckInDate().isBefore(LocalDate.now())) {
            throw new InvalidBookingException("Check-in date cannot be in the past");
        }

        // Check for conflicting bookings
        List<Booking> conflicts = bookingRepository.findConflictingBookings(
                bookingDTO.getRoomId(),
                bookingDTO.getCheckInDate(),
                bookingDTO.getCheckOutDate()
        );

        if (!conflicts.isEmpty()) {
            throw new RoomNotAvailableException("Room is not available for the selected dates");
        }

        // Get room
        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", bookingDTO.getRoomId()));

        // Create booking
        Booking booking = new Booking();
        booking.setGuestName(bookingDTO.getGuestName());
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setRoom(room);

        Booking savedBooking = bookingRepository.save(booking);
        return mapToDTO(savedBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        return mapToDTO(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getBookingsByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getBookingsByGuestName(String guestName) {
        return bookingRepository.findByGuestName(guestName).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getConflictingBookings(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        return bookingRepository.findConflictingBookings(roomId, checkInDate, checkOutDate).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));

        // Validate dates if changed
        if (!booking.getCheckInDate().equals(bookingDTO.getCheckInDate()) ||
            !booking.getCheckOutDate().equals(bookingDTO.getCheckOutDate())) {

            if (bookingDTO.getCheckInDate().isAfter(bookingDTO.getCheckOutDate())) {
                throw new InvalidBookingException("Check-in date must be before check-out date");
            }

            // Check for new conflicts
            List<Booking> conflicts = bookingRepository.findConflictingBookings(
                    booking.getRoom().getId(),
                    bookingDTO.getCheckInDate(),
                    bookingDTO.getCheckOutDate()
            );

            // Filter out the current booking from conflicts
            conflicts = conflicts.stream()
                    .filter(b -> !b.getId().equals(id))
                    .collect(Collectors.toList());

            if (!conflicts.isEmpty()) {
                throw new RoomNotAvailableException("Room is not available for the new dates");
            }

            booking.setCheckInDate(bookingDTO.getCheckInDate());
            booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        }

        booking.setGuestName(bookingDTO.getGuestName());

        Booking updatedBooking = bookingRepository.save(booking);
        return mapToDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking", "id", id);
        }
        bookingRepository.deleteById(id);
    }

    private BookingDTO mapToDTO(Booking booking) {
        return BookingDTO.builder()
                .id(booking.getId())
                .guestName(booking.getGuestName())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .roomId(booking.getRoom() != null ? booking.getRoom().getId() : null)
                .roomNumber(booking.getRoom() != null ? booking.getRoom().getRoomNumber() : null)
                .build();
    }
}

