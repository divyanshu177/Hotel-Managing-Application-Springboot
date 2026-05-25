# Quick Reference - Hotel Booking System

## 🏗️ Architecture Quick Overview

```
Controllers (REST API)
    ↓
Services (Business Logic)
    ↓
Repositories (Database Access)
    ↓
Entities (Data Model)
    ↓
MySQL Database
```

---

## 📁 File Locations

### Controllers
- `HotelController.java` → `/api/v1/hotels`
- `RoomController.java` → `/api/v1/rooms`
- `BookingController.java` → `/api/v1/bookings`

### Services
- `HotelService.java` (Interface)
- `HotelServiceImpl.java` (Implementation)
- `RoomService.java` (Interface)
- `RoomServiceImpl.java` (Implementation)
- `BookingService.java` (Interface)
- `BookingServiceImpl.java` (Implementation)

### Repositories
- `HotelRepository.java` (JPA)
- `RoomRepository.java` (JPA)
- `BookingRepository.java` (JPA)

### Entities
- `Hotel.java` (Entity)
- `Room.java` (Entity)
- `Booking.java` (Entity)

### DTOs
- `HotelDTO.java` (Data Transfer Object)
- `RoomDTO.java` (Data Transfer Object)
- `BookingDTO.java` (Data Transfer Object)

### Exceptions
- `ResourceNotFoundException.java`
- `ErrorResponse.java`
- `GlobalExceptionHandler.java`

### Configuration
- `OpenAPIConfiguration.java`
- `application.properties`

---

## 🔌 API Endpoints Quick Reference

### Hotels (3 endpoints)
```
POST   /api/v1/hotels                      Create hotel
GET    /api/v1/hotels/{id}                 Get by ID
GET    /api/v1/hotels                      Get all
```

### Rooms (2 endpoints)
```
POST   /api/v1/rooms?hotelId=X              Create room
GET    /api/v1/rooms                       Get all
```

### Bookings (2 endpoints)
```
POST   /api/v1/bookings                     Create booking
GET    /api/v1/bookings                     Get all
```

---

## 📊 Database Schema

### hotels
| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | Primary Key |
| name | VARCHAR(255) | NOT NULL |
| location | VARCHAR(255) | NOT NULL |

### rooms
| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | Primary Key |
| room_number | VARCHAR(255) | NOT NULL |
| price | DOUBLE | NOT NULL |
| hotel_id | BIGINT | Foreign Key |

### bookings
| Column | Type | Notes |
|--------|------|-------|
| id | BIGINT | Primary Key |
| guest_name | VARCHAR(255) | NOT NULL |
| check_in_date | DATE | NOT NULL |
| check_out_date | DATE | NOT NULL |
| room_id | BIGINT | Foreign Key |

---

## 🚀 Getting Started (5 Steps)

### Step 1: Create Database
```sql
CREATE DATABASE hotel_booking_db;
```

### Step 2: Update Configuration
Edit `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Step 3: Build Project
```bash
mvn clean install
```

### Step 4: Run Application
```bash
mvn spring-boot:run
```

### Step 5: Access API
```
http://localhost:8080/swagger-ui.html
```

---

## 📝 Sample API Calls

### Create Hotel
```json
POST /api/v1/hotels
{
  "name": "Grand Hotel",
  "location": "New York"
}
```

### Create Room
```json
POST /api/v1/rooms?hotelId=1
{
  "roomNumber": "101",
  "price": 150.00
}
```

### Create Booking
```json
POST /api/v1/bookings
{
  "guestName": "John Doe",
  "checkInDate": "2026-04-01",
  "checkOutDate": "2026-04-05",
  "roomId": 1
}
```

---

## 🔍 Common Queries

### Get All Hotels
```
GET /api/v1/hotels
```

### Get All Rooms
```
GET /api/v1/rooms
```

### Get All Bookings
```
GET /api/v1/bookings
```

---

## ✅ Validation Rules

### Hotel
- Name: 2-100 chars, required
- Location: Required

### Room
- Room Number: Required
- Price: > 0, required
- Hotel ID: Required

### Booking
- Guest Name: Required
- Check-in Date: Required
- Check-out Date: Required
- Room ID: Required

---

## 🛠️ Troubleshooting

### Database Connection Failed
```bash
# Check MySQL is running
# Verify credentials in application.properties
# Ensure database exists
```

### Port 8080 Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### Table Already Exists
```sql
DROP DATABASE hotel_booking_db;
CREATE DATABASE hotel_booking_db;
```

---

## 📚 Documentation Files

| File | Purpose |
|------|---------|
| ARCHITECTURE.md | System design |
| API_DOCUMENTATION.md | API reference with examples |
| GETTING_STARTED.md | Setup, installation & troubleshooting |
| QUICK_REFERENCE.md | This file - quick lookups |

---

## 🔗 Relationships
```
Hotel (1) -----> (Many) Room
Hotel (1) -----> (Many) Booking (via Room)
Room (1) -----> (Many) Booking
```

---

## 💾 Database Setup Script
```sql
-- Create database
CREATE DATABASE hotel_booking_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use database
USE hotel_booking_db;

-- Tables will be auto-created by JPA on application startup
```

---

## 🎨 Response Format

### Success (200 OK)
```json
{
  "id": 1,
  "name": "Grand Hotel",
  "location": "New York"
}
```

### Error (400/404/500)
```json
{
  "timestamp": "2026-03-16T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Hotel not found with id: '999'"
}
```

---

## 🔐 HTTP Methods

| Method | Purpose | Status |
|--------|---------|--------|
| GET | Retrieve data | 200 |
| POST | Create data | 201 |
| PUT | Update data | 200 |
| PATCH | Partial update | 204 |
| DELETE | Delete data | 204 |

---

## 📱 Swagger UI Access

```
Base URL:  http://localhost:8080
Swagger:   http://localhost:8080/swagger-ui.html
API Docs:  http://localhost:8080/api-docs
```

---

**Last Updated:** 2026-03-16  
**Version:** 1.0.0  
**Status:** Complete & Ready to Use ✓
