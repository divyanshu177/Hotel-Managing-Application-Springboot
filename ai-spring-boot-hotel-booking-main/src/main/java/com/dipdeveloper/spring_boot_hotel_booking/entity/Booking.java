package com.dipdeveloper.spring_boot_hotel_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Booking Entity
 * Represents a hotel room booking with guest information and dates
 */
@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    /**
     * Many-to-One relationship: Many Bookings belong to One Room
     * - @JoinColumn: Defines the foreign key column
     * - FetchType.LAZY: Room is loaded only when accessed
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}



