# Hotel Booking System - Spring Boot 4 Architecture

## Overview
This is a basic Spring Boot 4 backend architecture for a hotel booking system. The application follows a layered architecture pattern with clear separation of concerns across multiple layers.

## Architecture Layers

### 1. **Entity Layer** (`entity` package)
Represents the domain model and database tables using JPA annotations.

#### Entities:
- **Hotel** - Represents hotel information with name and location
- **Room** - Represents individual rooms within hotels with room number and price
- **Booking** - Represents guest bookings with check-in/check-out dates

**Key Features:**
- JPA annotations for ORM mapping
- Validation constraints using Jakarta Validation
- Relationship mappings (One-to-Many, Many-to-One)

### 2. **DTO Layer** (`dto` package)
Data Transfer Objects for API request/response handling.

#### DTOs:
- **HotelDTO** - For hotel data transfer
- **RoomDTO** - For room data transfer with hotel details
- **BookingDTO** - For booking data transfer with room information

**Key Features:**
- JSON serialization/deserialization support
- Validation annotations
- Separation from entity layer

### 3. **Repository Layer** (`repository` package)
Spring Data JPA repositories for database operations.

#### Repositories:
- **HotelRepository** - Basic CRUD operations for hotels
- **RoomRepository** - Basic CRUD operations for rooms
- **BookingRepository** - Basic CRUD operations for bookings

**Key Features:**
- Spring Data JPA interfaces
- Basic query methods

### 4. **Service Layer** (`service` package)
Business logic and transaction management.

#### Interfaces:
- **HotelService** - Interface for hotel operations
- **RoomService** - Interface for room operations
- **BookingService** - Interface for booking operations

#### Implementations (`service/impl`):
- **HotelServiceImpl** - Implementation of hotel operations
- **RoomServiceImpl** - Implementation of room operations
- **BookingServiceImpl** - Implementation of booking operations

**Key Features:**
- @Transactional annotation for transaction management
- Basic business logic validation
- DTO mapping

### 5. **Controller Layer** (`controller` package)
REST API endpoints for client communication.

#### Controllers:
- **HotelController** - REST endpoints for hotel management
- **RoomController** - REST endpoints for room management
- **BookingController** - REST endpoints for booking management

**Key Features:**
- RESTful API design
- Request/response handling
- HTTP status codes
- Basic input validation

### 6. **Exception Handling** (`exception` package)

#### Custom Exceptions:
- **ResourceNotFoundException** - When a resource is not found
- **GlobalExceptionHandler** - Centralized exception handling with @RestControllerAdvice

#### Response DTO:
- **ErrorResponse** - Structured error response format

**Key Features:**
- Centralized exception handling
- Consistent error response format

## Database Schema

### Hotels Table
```sql
- id (PK)
- name
- location
```

### Rooms Table
```sql
- id (PK)
- room_number
- price
- hotel_id (FK)
```

### Bookings Table
```sql
- id (PK)
- guest_name
- check_in_date
- check_out_date
- room_id (FK)
```

## API Endpoints

### Hotel Management
- `POST /api/v1/hotels` - Create a new hotel
- `GET /api/v1/hotels/{id}` - Get hotel by ID
- `GET /api/v1/hotels` - Get all hotels

### Room Management
- `POST /api/v1/rooms` - Create room in hotel
- `GET /api/v1/rooms` - Get all rooms

### Booking Management
- `POST /api/v1/bookings` - Create booking
- `GET /api/v1/bookings` - Get all bookings

## Key Features

### 1. **Validation**
- Jakarta Validation annotations
- Custom validation in service layer

### 2. **Exception Handling**
- Custom exceptions with meaningful messages
- Global exception handler for consistent responses

### 3. **Transaction Management**
- @Transactional annotations for data consistency

## Setup and Configuration

### Prerequisites
- Java 21
- MySQL 8.0+
- Maven 3.6+
- Spring Boot 4.0.3

### Database Setup
```sql
CREATE DATABASE hotel_booking_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Configuration
Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Running the Application
```bash
mvn clean install
mvn spring-boot:run
```

### Access Swagger UI
```
http://localhost:8080/swagger-ui.html
```

## Dependencies
- Spring Boot 4.0.3
- Spring Data JPA
- Spring Validation
- Spring Web MVC
- MySQL Connector
- Lombok
- SpringDoc OpenAPI (Swagger)

## Design Patterns Used

1. **Repository Pattern** - Abstraction over data access
2. **Service Pattern** - Business logic separation
3. **DTO Pattern** - Data transfer abstraction
4. **Singleton Pattern** - Spring beans
5. **Factory Pattern** - Spring dependency injection

## Best Practices Implemented

1. ✅ Layered architecture with clear separation
2. ✅ DTOs for API contracts
3. ✅ Custom exceptions for error handling
4. ✅ Input validation at multiple levels
5. ✅ Transaction management with @Transactional
6. ✅ RESTful API design principles
7. ✅ Consistent naming conventions
8. ✅ Proper HTTP status codes

## Future Enhancements

1. Add more endpoints for advanced operations
2. Implement user authentication and authorization
3. Add payment integration
4. Implement review and rating system
5. Add email notifications
6. Implement caching layer
7. Add API rate limiting
8. Implement advanced search filters
9. Add transaction audit logging
10. Implement API versioning

---

**Version:** 1.0.0  
**Last Updated:** 2026-03-16
