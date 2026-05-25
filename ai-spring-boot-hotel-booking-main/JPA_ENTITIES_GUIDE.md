# JPA Entities - Hotel Booking System

## Summary

Three basic JPA entities have been created with relationships for a hotel booking system:

### 1. Hotel Entity
**File:** `Hotel.java`

```java
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Room> rooms;
}
```

**Fields:**
- `id` - Primary Key (Auto-generated)
- `name` - Hotel name
- `location` - Hotel location
- `rooms` - One-to-Many relationship with Room entity

---

### 2. Room Entity
**File:** `Room.java`

```java
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Booking> bookings;
}
```

**Fields:**
- `id` - Primary Key (Auto-generated)
- `roomNumber` - Room number/identifier
- `price` - Room price per night
- `hotel` - Many-to-One relationship with Hotel entity
- `bookings` - One-to-Many relationship with Booking entity

---

### 3. Booking Entity
**File:** `Booking.java`

```java
@Entity
@Table(name = "bookings")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
```

**Fields:**
- `id` - Primary Key (Auto-generated)
- `guestName` - Guest name
- `checkInDate` - Check-in date
- `checkOutDate` - Check-out date
- `room` - Many-to-One relationship with Room entity

---

## JPA Relationships

### Entity Relationship Diagram

```
┌─────────────────────┐
│      HOTELS         │
├─────────────────────┤
│ • id (PK)           │
│ • name              │
│ • location          │
└──────────┬──────────┘
           │
    (1:M)  │
           │
┌──────────▼──────────┐
│      ROOMS          │
├─────────────────────┤
│ • id (PK)           │
│ • roomNumber        │
│ • price             │
│ • hotel_id (FK)     │
└──────────┬──────────┘
           │
    (1:M)  │
           │
┌──────────▼──────────┐
│    BOOKINGS         │
├─────────────────────┤
│ • id (PK)           │
│ • guestName         │
│ • checkInDate       │
│ • checkOutDate      │
│ • room_id (FK)      │
└─────────────────────┘
```

---

## Relationship Details

### Hotel → Room (One-to-Many)

```java
@OneToMany(
    mappedBy = "hotel",           // Room has a field called 'hotel'
    cascade = CascadeType.ALL,    // All operations cascade to rooms
    orphanRemoval = true,         // Remove room when removed from hotel
    fetch = FetchType.LAZY        // Load rooms on demand
)
private List<Room> rooms;
```

**Meaning:** One hotel can have many rooms.

---

### Room → Hotel (Many-to-One)

```java
@ManyToOne(fetch = FetchType.LAZY)        // Load hotel on demand
@JoinColumn(name = "hotel_id", nullable = false)  // Foreign key
private Hotel hotel;
```

**Meaning:** Many rooms belong to one hotel.

---

### Room → Booking (One-to-Many)

```java
@OneToMany(
    mappedBy = "room",            // Booking has a field called 'room'
    cascade = CascadeType.ALL,    // All operations cascade to bookings
    orphanRemoval = true,         // Remove booking when removed from room
    fetch = FetchType.LAZY        // Load bookings on demand
)
private List<Booking> bookings;
```

**Meaning:** One room can have many bookings.

---

### Booking → Room (Many-to-One)

```java
@ManyToOne(fetch = FetchType.LAZY)        // Load room on demand
@JoinColumn(name = "room_id", nullable = false)  // Foreign key
private Room room;
```

**Meaning:** Many bookings belong to one room.

---

## Database Tables

### hotels table
```sql
CREATE TABLE hotels (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);
```

### rooms table
```sql
CREATE TABLE rooms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    hotel_id BIGINT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);
```

### bookings table
```sql
CREATE TABLE bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(255) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    room_id BIGINT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);
```

---

## Usage Examples

### Creating a Hotel with Rooms

```java
Hotel hotel = new Hotel();
hotel.setName("Grand Hotel");
hotel.setLocation("New York");

Room room1 = new Room();
room1.setRoomNumber("101");
room1.setPrice(100.0);
room1.setHotel(hotel);

List<Room> rooms = new ArrayList<>();
rooms.add(room1);
hotel.setRooms(rooms);

// Save to database
hotelRepository.save(hotel);  // Rooms are saved automatically due to CascadeType.ALL
```

### Creating a Booking

```java
Booking booking = new Booking();
booking.setGuestName("John Doe");
booking.setCheckInDate(LocalDate.of(2026, 3, 20));
booking.setCheckOutDate(LocalDate.of(2026, 3, 25));
booking.setRoom(room);

// Save to database
bookingRepository.save(booking);
```

### Querying

```java
// Get a hotel with all its rooms
Hotel hotel = hotelRepository.findById(1L).get();
List<Room> rooms = hotel.getRooms();  // Rooms are loaded on demand (lazy loading)

// Get a room with all its bookings
Room room = roomRepository.findById(1L).get();
List<Booking> bookings = room.getBookings();  // Bookings are loaded on demand

// Get a booking with its room details
Booking booking = bookingRepository.findById(1L).get();
Room room = booking.getRoom();  // Room is loaded on demand
```

---

## Key JPA Features Used

### @Entity
- Marks the class as a JPA entity mapped to a database table

### @Table
- Specifies the table name in the database

### @Id
- Marks the field as the primary key

### @GeneratedValue
- Specifies how the primary key is generated (IDENTITY = auto-increment)

### @Column
- Configures column properties (nullable, length, etc.)

### @OneToMany
- Represents a one-to-many relationship from the "one" side
- `mappedBy`: Specifies the field in the related entity that owns the relationship
- `cascade`: Defines cascade operations (ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH)
- `orphanRemoval`: Removes child entities when removed from parent
- `fetch`: Specifies loading strategy (LAZY, EAGER)

### @ManyToOne
- Represents a many-to-one relationship from the "many" side
- `fetch`: Specifies loading strategy (LAZY, EAGER)

### @JoinColumn
- Specifies the foreign key column in the database
- `name`: The column name
- `nullable`: Whether the column can be NULL

### @Data (Lombok)
- Auto-generates getters, setters, toString, equals, and hashCode

### @NoArgsConstructor (Lombok)
- Generates a no-argument constructor

### @AllArgsConstructor (Lombok)
- Generates a constructor with all fields as parameters

---

## Benefits of This Design

✅ **Proper Relationships** - Clear one-to-many and many-to-one mappings  
✅ **Cascade Operations** - Automatically manage related entities  
✅ **Lazy Loading** - Optimize database queries  
✅ **Referential Integrity** - Foreign key constraints enforce data integrity  
✅ **Orphan Removal** - Automatically delete orphaned entities  
✅ **Type Safety** - Strong typing with entity classes  
✅ **Simplified Code** - Lombok reduces boilerplate code  

---

## Next Steps

1. Create repositories (HotelRepository, RoomRepository, BookingRepository)
2. Create services to handle business logic
3. Create controllers to expose REST endpoints
4. Configure database connection in application.properties
5. Run the application and test the entities

---

**Status:** ✅ Basic JPA Entities Created Successfully
