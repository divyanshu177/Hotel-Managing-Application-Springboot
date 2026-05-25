# Hotel Booking System - Spring Boot 4

> A basic Spring Boot 4 backend for hotel booking management with layered architecture.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue)
![License](https://img.shields.io/badge/License-Apache%202.0-green)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Quick Start](#quick-start)
- [API Endpoints](#api-endpoints)
- [Documentation](#documentation)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

---

## 🌟 Overview

This project is a **basic Spring Boot 4 backend** for managing hotel bookings. It demonstrates:

- **Layered Architecture** - Clear separation of concerns
- **RESTful API Design** - Basic CRUD endpoints
- **Spring Data JPA** - Database operations
- **Custom Exceptions** - Error handling
- **API Documentation** - Swagger UI integration
- **Validation** - Input validation

---

## ✨ Features

### 🏨 Hotel Management
- Create and read hotels
- Basic hotel information (name, location)
- Hotel-room relationships

### 🛏️ Room Management
- Create and read rooms
- Room pricing and hotel association
- Room-booking relationships

### 📅 Booking System
- Create and read bookings
- Guest information and dates
- Room booking associations

### ✔️ Validation & Error Handling
- Basic input validation
- Custom exceptions
- Global exception handler

### 📚 API Documentation
- **Interactive Swagger UI** - Test endpoints directly
- **Basic API docs** - Request/response examples

---

## 🏗️ Architecture

### Layered Architecture Pattern

```
┌─────────────────────────────────────┐
│      REST API Layer                 │
│   (Controllers - HTTP Interface)    │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│      Service Layer                  │
│   (Business Logic & Validation)     │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│      Repository Layer               │
│   (Data Access & Queries)           │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│      Entity Layer                   │
│   (Domain Model)                    │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│      MySQL Database                 │
│   (Data Persistence)                │
└─────────────────────────────────────┘
```

---

## 🚀 Quick Start

### 1. Prerequisites
```bash
# Check Java version (should be 21+)
java -version

# Check Maven version (should be 3.6+)
mvn -version

# MySQL should be running
mysql -u root -p
```

### 2. Create Database
```sql
CREATE DATABASE hotel_booking_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Update Configuration
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Build & Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Or run the JAR file
java -jar target/ai-spring-boot-hotel-booking-0.0.1-SNAPSHOT.jar
```

### 5. Access the API
```
Swagger UI:  http://localhost:8080/swagger-ui.html
API Docs:    http://localhost:8080/api-docs
Base URL:    http://localhost:8080/api/v1
```

---

## 🔌 API Endpoints

### Hotels (3 endpoints)
```
POST   /api/v1/hotels                              Create hotel
GET    /api/v1/hotels                              Get all hotels
GET    /api/v1/hotels/{id}                         Get hotel by ID
```

### Rooms (2 endpoints)
```
POST   /api/v1/rooms?hotelId={hotelId}              Create room
GET    /api/v1/rooms                               Get all rooms
```

### Bookings (2 endpoints)
```
POST   /api/v1/bookings                             Create booking
GET    /api/v1/bookings                             Get all bookings
```

**Total: 7 API Endpoints**

---

## 📚 Documentation

This project includes basic documentation:

| Document | Purpose |
|----------|---------|
| **[ARCHITECTURE.md](ARCHITECTURE.md)** | Architecture overview |
| **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)** | API reference |
| **[GETTING_STARTED.md](GETTING_STARTED.md)** | Setup guide |
| **[QUICK_REFERENCE.md](QUICK_REFERENCE.md)** | Quick reference |
| **[IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md)** | Implementation details |

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 21 | Programming language |
| **Spring Boot** | 4.0.3 | Application framework |
| **Spring Data JPA** | Latest | ORM framework |
| **Hibernate** | Latest | JPA implementation |
| **MySQL** | 8.0+ | Database |
| **Jakarta EE** | Latest | Java EE standards |
| **Lombok** | Latest | Code generation |
| **SpringDoc OpenAPI** | 3.0.2 | API documentation |
| **Maven** | 3.6+ | Build tool |

---

## 📁 Project Structure

```
ai-spring-boot-hotel-booking/
├── src/main/java/com/dipdeveloper/ai_spring_boot_hotel_booking/
│   ├── controller/
│   │   ├── HotelController.java
│   │   ├── RoomController.java
│   │   └── BookingController.java
│   ├── service/
│   │   ├── HotelService.java (interface)
│   │   ├── RoomService.java (interface)
│   │   ├── BookingService.java (interface)
│   │   └── impl/
│   │       ├── HotelServiceImpl.java
│   │       ├── RoomServiceImpl.java
│   │       └── BookingServiceImpl.java
│   ├── repository/
│   │   ├── HotelRepository.java
│   │   ├── RoomRepository.java
│   │   └── BookingRepository.java
│   ├── entity/
│   │   ├── Hotel.java
│   │   ├── Room.java
│   │   └── Booking.java
│   ├── dto/
│   │   ├── HotelDTO.java
│   │   ├── RoomDTO.java
│   │   └── BookingDTO.java
│   ├── exception/
│   │   ├── ResourceNotFoundException.java
│   │   ├── RoomNotAvailableException.java
│   │   ├── InvalidBookingException.java
│   │   ├── ErrorResponse.java
│   │   └── GlobalExceptionHandler.java
│   ├── config/
│   │   └── OpenAPIConfiguration.java
│   └── AiSpringBootHotelBookingApplication.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
├── ARCHITECTURE.md
├── API_DOCUMENTATION.md
├── GETTING_STARTED.md
├── QUICK_REFERENCE.md
├── IMPLEMENTATION_SUMMARY.md
└── README.md (this file)
```

---

## 💾 Database Schema

### hotels table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | NOT NULL |
| location | VARCHAR(255) | NOT NULL |

### rooms table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| room_number | VARCHAR(255) | NOT NULL |
| price | DOUBLE | NOT NULL |
| hotel_id | BIGINT | FOREIGN KEY (hotels.id) |

### bookings table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| guest_name | VARCHAR(255) | NOT NULL |
| check_in_date | DATE | NOT NULL |
| check_out_date | DATE | NOT NULL |
| room_id | BIGINT | FOREIGN KEY (rooms.id) |

---

## 🔐 Validation Rules

### Hotel Validation
- **Name**: Required, 2-100 characters
- **Location**: Required

### Room Validation
- **Room Number**: Required
- **Price**: Required, must be > 0
- **Hotel ID**: Required

### Booking Validation
- **Guest Name**: Required
- **Check-in Date**: Required
- **Check-out Date**: Required
- **Room ID**: Required

---

## 🧪 Testing

### Using Swagger UI (Recommended)
1. Run the application: `mvn spring-boot:run`
2. Open browser: `http://localhost:8080/swagger-ui.html`
3. Click on any endpoint to expand it
4. Click "Try it out" to test
5. Enter parameters and click "Execute"

### Using cURL
```bash
# Get all hotels
curl http://localhost:8080/api/v1/hotels

# Create a hotel
curl -X POST http://localhost:8080/api/v1/hotels \
  -H "Content-Type: application/json" \
  -d '{
    "name":"Test Hotel",
    "location":"New York"
  }'
```

---

## 📦 Installation

### System Requirements
- Java Development Kit (JDK) 21 or higher
- MySQL Server 8.0 or higher
- Maven 3.6 or higher
- Git (optional)

### Step-by-step Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-repo/ai-spring-boot-hotel-booking.git
   cd ai-spring-boot-hotel-booking
   ```

2. **Create MySQL database**
   ```sql
   CREATE DATABASE hotel_booking_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Configure database connection**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

---

## ⚙️ Configuration

### application.properties
```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db
spring.datasource.username=root
spring.datasource.password=root

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Logging
logging.level.root=INFO
logging.level.com.dipdeveloper.ai_spring_boot_hotel_booking=DEBUG

# OpenAPI/Swagger
springdoc.swagger-ui.enabled=true
```

---

## 🏃 Running the Application

### Option 1: Maven
```bash
mvn spring-boot:run
```

### Option 2: JAR File
```bash
mvn clean package
java -jar target/ai-spring-boot-hotel-booking-0.0.1-SNAPSHOT.jar
```

### Option 3: IDE
1. Import project into your IDE
2. Right-click main class: `AiSpringBootHotelBookingApplication`
3. Select "Run as Java Application"

---

## 🐛 Troubleshooting

### Connection Refused
- Ensure MySQL is running
- Verify credentials in `application.properties`
- Check database exists

### Port Already in Use
- Change port in `application.properties`: `server.port=8081`
- Or kill the process: `lsof -i :8080` (Mac/Linux)

### Build Failures
- Clear Maven cache: `mvn clean`
- Check Java version: `java -version`
- Run: `mvn clean install -U`

For more help, see [GETTING_STARTED.md](GETTING_STARTED.md)

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/AmazingFeature`
3. Commit changes: `git commit -m 'Add some AmazingFeature'`
4. Push to branch: `git push origin feature/AmazingFeature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

---

## 👨‍💻 Author

**Hotel Booking System Development Team**
- YouTube: [Dip Developer Channel](https://www.youtube.com/@DipDeveloper)
- GitHub: [Project Repository](https://github.com/TheDipDeveloper/ai-spring-boot-hotel-booking)

---

## 🙏 Acknowledgments

- Spring Boot Team for the excellent framework
- Spring Data JPA team for ORM support
- SpringDoc team for OpenAPI/Swagger integration
- MySQL team for the reliable database

---

## 📞 Support

For issues, questions, or suggestions:

1. **Check Documentation**: Read ARCHITECTURE.md, API_DOCUMENTATION.md, GETTING_STARTED.md
2. **Review Logs**: Check application console output
3. **Search Issues**: Look for similar problems in GitHub Issues
4. **Create Issue**: Open a GitHub issue with detailed information

---

## 🗺️ Roadmap

### Version 1.0 (Current)
- ✅ Basic hotel CRUD operations
- ✅ Basic room management
- ✅ Basic booking system
- ✅ API documentation

### Version 1.1 (Planned)
- ⏳ Enhanced validation
- ⏳ More endpoints
- ⏳ Advanced features

---

## 📊 Project Statistics

- **Total Java Classes**: 29
- **Total Lines of Code**: ~1,000
- **API Endpoints**: 7
- **Database Tables**: 3
- **Documentation Pages**: 5

---

## ✅ Quality Checklist

- ✅ Layered architecture implemented
- ✅ Spring Data JPA integration
- ✅ MySQL database configured
- ✅ RESTful API design
- ✅ Exception handling
- ✅ Input validation
- ✅ API documentation (Swagger)
- ✅ Transaction management
- ✅ Code best practices
- ✅ Basic documentation

---

## 📝 Changelog

### Version 1.0.0 - 2026-03-16
- Initial release
- Basic hotel booking system
- 7 API endpoints
- Basic API documentation
- Setup guides

---

**Status**: ✅ **Basic Implementation Ready**

For detailed information, refer to the documentation files included in this project.

