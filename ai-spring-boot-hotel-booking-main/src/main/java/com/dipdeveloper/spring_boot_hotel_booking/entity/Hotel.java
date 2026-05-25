package com.dipdeveloper.spring_boot_hotel_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Hotel Entity
 * Represents a hotel with basic information and relationships to rooms
 */
@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    /**
     * One-to-Many relationship: One Hotel has many Rooms
     * - mappedBy = "hotel": Room has a field called 'hotel'
     * - cascade = CascadeType.ALL: All operations cascade to rooms
     * - orphanRemoval = true: Removes rooms when removed from hotel
     * - fetch = FetchType.LAZY: Rooms loaded on demand
     */
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Room> rooms;
}


