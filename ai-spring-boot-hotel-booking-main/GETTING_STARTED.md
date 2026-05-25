# Getting Started Guide - Hotel Booking System

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Installation](#installation)
3. [Database Setup](#database-setup)
4. [Configuration](#configuration)
5. [Running the Application](#running-the-application)
6. [Testing the API](#testing-the-api)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisites

Before you begin, make sure you have the following installed:

### Required Software
- **Java Development Kit (JDK) 21**
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Verify installation: `java -version`

- **MySQL Server 8.0+**
  - Download from: https://www.mysql.com/downloads/
  - Ensure MySQL service is running

- **Apache Maven 3.6+**
  - Download from: https://maven.apache.org/download.cgi
  - Verify installation: `mvn -version`

- **Git** (optional, for cloning the repository)
  - Download from: https://git-scm.com/

### Recommended Tools
- **IDE:** IntelliJ IDEA, Eclipse, or VS Code
- **API Client:** Postman, Insomnia, or Thunder Client
- **MySQL Client:** MySQL Workbench or DBeaver

---

## Installation

### Step 1: Clone the Repository
```bash
git clone https://github.com/TheDipDeveloper/ai-spring-boot-hotel-booking.git
cd ai-spring-boot-hotel-booking
```

### Step 2: Verify Project Structure
```
ai-spring-boot-hotel-booking/
├── src/
│   ├── main/
│   │   ├── java/com/dipdeveloper/ai_spring_boot_hotel_booking/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── config/
│   │   │   └── AiSpringBootHotelBookingApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── ARCHITECTURE.md
├── API_DOCUMENTATION.md
├── GETTING_STARTED.md
└── README.md
```

### Step 3: Install Dependencies
```bash
mvn clean install
```

This command will:
- Download all required dependencies
- Compile the source code
- Run tests
- Package the application

---

## Database Setup

### Step 1: Create Database
Open MySQL command line or your preferred MySQL client:

```sql
-- Create database
CREATE DATABASE hotel_booking_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Verify database creation
SHOW DATABASES;
```

### Step 2: Verify MySQL Connection
```bash
mysql -u root -p -h localhost
```

Enter your MySQL password when prompted.

### Step 3: Tables Creation
The application will automatically create tables when it starts for the first time. The following tables will be created:

- **hotels** - Store hotel information
- **rooms** - Store room details
- **bookings** - Store booking records

You can verify table creation by running:
```sql
USE hotel_booking_db;
SHOW TABLES;
```

---

## Configuration

### Step 1: Update Application Properties
Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Logging Configuration
logging.level.root=INFO
logging.level.com.dipdeveloper.ai_spring_boot_hotel_booking=DEBUG
```

### Step 2: Update MySQL Password
Replace `your_mysql_password` with your actual MySQL root password.

**Example:**
```properties
spring.datasource.password=MySecurePassword123
```

### Step 3: Verify Configuration
Make sure:
- MySQL is running and accessible
- Database `hotel_booking_db` exists
- Username and password are correct

---

## Running the Application

### Option 1: Run using Maven
```bash
mvn spring-boot:run
```

### Option 2: Run using JAR file
```bash
# Package the application
mvn clean package

# Run the JAR file
java -jar target/ai-spring-boot-hotel-booking-0.0.1-SNAPSHOT.jar
```

### Option 3: Run from IDE
1. Import project into your IDE
2. Right-click on `AiSpringBootHotelBookingApplication.java`
3. Select "Run As" → "Java Application"

### Expected Output
```
Started AiSpringBootHotelBookingApplication in X seconds (JVM running for X seconds)
```

### Verify Application is Running
Check if the application is accessible:
```bash
curl http://localhost:8080/api-docs
```

---

## Testing the API

### Method 1: Using Swagger UI (Recommended)
1. Open your browser
2. Navigate to: `http://localhost:8080/swagger-ui.html`
3. You'll see the interactive API documentation
4. Click on any endpoint to expand it
5. Click "Try it out" to test the endpoint
6. Enter parameters and click "Execute"

### Method 2: Using Postman
1. Download and install Postman
2. Create a new request
3. Select HTTP method (GET, POST, PUT, DELETE)
4. Enter URL: `http://localhost:8080/api/v1/hotels`
5. Add headers (if needed): `Content-Type: application/json`
6. Add request body (if needed)
7. Click "Send"

### Method 3: Using cURL
```bash
# Get all hotels
curl -X GET http://localhost:8080/api/v1/hotels

# Create a new hotel
curl -X POST http://localhost:8080/api/v1/hotels \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Hotel",
    "address": "123 Main St",
    "city": "New York",
    "country": "USA",
    "phoneNumber": "+1-212-555-1234",
    "description": "A test hotel"
  }'
```

### Sample Test Flow

#### 1. Create a Hotel
```bash
POST /api/v1/hotels
{
  "name": "Grand Hotel",
  "address": "123 Main Street",
  "city": "New York",
  "country": "USA",
  "phoneNumber": "+1-212-555-1234",
  "description": "A luxury hotel"
}
```

#### 2. Create a Room
```bash
POST /api/v1/rooms/hotel/1
{
  "roomNumber": "101",
  "roomType": "DOUBLE",
  "pricePerNight": 150.00,
  "capacity": 2,
  "description": "Comfortable double room"
}
```

#### 3. Create a Booking
```bash
POST /api/v1/bookings
{
  "guestEmail": "john@example.com",
  "guestName": "John Doe",
  "guestPhoneNumber": "+1-555-0123",
  "checkInDate": "2026-04-01",
  "checkOutDate": "2026-04-05",
  "numberOfGuests": 2,
  "hotelId": 1,
  "roomId": 1
}
```

#### 4. Get Booking Details
```bash
GET /api/v1/bookings/1
```

---

## Troubleshooting

### Issue 1: Connection to Database Failed
**Error Message:** `java.sql.SQLException: Access denied for user 'root'@'localhost'`

**Solution:**
1. Verify MySQL is running
2. Check username and password in `application.properties`
3. Ensure database `hotel_booking_db` exists
4. Try connecting manually: `mysql -u root -p`

---

### Issue 2: Table Already Exists
**Error Message:** `Table 'hotels' already exists`

**Solution:**
1. Open MySQL command line
2. Run: `DROP DATABASE hotel_booking_db;`
3. Create database again: `CREATE DATABASE hotel_booking_db;`
4. Restart the application

---

### Issue 3: Maven Build Failure
**Error Message:** `Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin`

**Solution:**
1. Clear Maven cache: `mvn clean`
2. Verify Java version: `java -version` (should be 21)
3. Run: `mvn clean install -U`

---

### Issue 4: Port Already in Use
**Error Message:** `Port 8080 is already in use`

**Solution:**
1. Change the port in `application.properties`:
   ```properties
   server.port=8081
   ```
2. Or kill the process using port 8080:
   - **Windows:** `netstat -ano | findstr :8080` then `taskkill /PID <PID> /F`
   - **Linux/Mac:** `lsof -i :8080` then `kill -9 <PID>`

---

### Issue 5: Swagger UI Not Loading
**Error Message:** `Failed to load swagger-ui` or blank page

**Solution:**
1. Wait a few seconds for the application to fully start
2. Clear browser cache (Ctrl+Shift+Delete)
3. Try accessing: `http://localhost:8080/swagger-ui/index.html`
4. Check logs for errors: Look for "Started" message

---

### Issue 6: Validation Errors in Booking
**Error Message:** `Check-in date cannot be in the past`

**Solution:**
- Use a future date for `checkInDate`
- Current date: 2026-03-16
- Use dates like: 2026-04-01, 2026-04-15, etc.

---

### Issue 7: Room Not Available Error
**Error Message:** `Room is not available for the selected dates`

**Solution:**
1. Check if room is marked as available: `GET /api/v1/rooms/{id}`
2. Check for conflicting bookings: `GET /api/v1/bookings/room/{roomId}/conflicts?checkInDate=...&checkOutDate=...`
3. Use different dates or select a different room

---

## Common Commands

### View Application Logs
```bash
# Live logs while running with Maven
mvn spring-boot:run | grep "ERROR\|WARN"

# From application output
# Check console for DEBUG and INFO messages
```

### Check Database Tables
```sql
USE hotel_booking_db;
DESCRIBE hotels;
DESCRIBE rooms;
DESCRIBE bookings;
```

### View Sample Data
```sql
SELECT * FROM hotels;
SELECT * FROM rooms;
SELECT * FROM bookings;
```

### Reset Database
```sql
DROP DATABASE hotel_booking_db;
CREATE DATABASE hotel_booking_db;
-- Restart application to recreate tables
```

---

## Next Steps

1. **Read the Architecture Document:** `ARCHITECTURE.md`
2. **Review API Documentation:** `API_DOCUMENTATION.md`
3. **Explore the Code:** Check the implementation in `src/main/java`
4. **Add Sample Data:** Use Swagger UI to create hotels, rooms, and bookings
5. **Integrate with Frontend:** Connect this API to a frontend application

---

## Support

For issues or questions:
1. Check the logs: Look in console output
2. Review API documentation: `API_DOCUMENTATION.md`
3. Check error responses: They contain detailed error messages
4. Review architecture: `ARCHITECTURE.md` for design details

---

**Last Updated:** 2026-03-16  
**Version:** 1.0.0

