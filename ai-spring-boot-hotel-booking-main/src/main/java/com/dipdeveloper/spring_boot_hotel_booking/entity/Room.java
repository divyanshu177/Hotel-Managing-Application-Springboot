package com.dipdeveloper.spring_boot_hotel_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Room Entity
 * Represents a room in a hotel with pricing and relationships
 */
@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Double price;

    /**
     * Many-to-One relationship: Many Rooms belong to One Hotel
     * - @JoinColumn: Defines the foreign key column
     * - FetchType.LAZY: Hotel is loaded only when accessed
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    /**
     * One-to-Many relationship: One Room has many Bookings
     * - mappedBy = "room": Booking has a field called 'room'
     * - cascade = CascadeType.ALL: All operations cascade to bookings
     * - orphanRemoval = true: Removes bookings when removed from room
     */
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Booking> bookings;
}


