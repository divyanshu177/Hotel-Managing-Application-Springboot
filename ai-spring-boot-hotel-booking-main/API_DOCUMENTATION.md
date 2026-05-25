# Hotel Booking System API Documentation

## Overview

This document provides detailed API documentation for the Hotel Booking System. The API follows RESTful principles and provides basic CRUD operations for hotels, rooms, and bookings.

## Base URL
```
http://localhost:8080/api/v1
```

## Authentication
Currently, no authentication is required for API access.

## Response Format
All responses are in JSON format.

### Success Response
```json
{
  "id": 1,
  "name": "Hotel Name",
  ...
}
```

### Error Response
```json
{
  "timestamp": "2026-03-16T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Hotel not found with id: '999'",
  "path": "/api/v1/hotels/999"
}
```

## Hotels API

### Create Hotel
Create a new hotel.

**Endpoint:** `POST /api/v1/hotels`

**Request Body:**
```json
{
  "name": "Grand Hotel",
  "location": "New York"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "Grand Hotel",
  "location": "New York"
}
```

### Get All Hotels
Retrieve all hotels.

**Endpoint:** `GET /api/v1/hotels`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Grand Hotel",
    "location": "New York"
  }
]
```

### Get Hotel by ID
Retrieve a specific hotel by ID.

**Endpoint:** `GET /api/v1/hotels/{id}`

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Grand Hotel",
  "location": "New York"
}
```

## Rooms API

### Create Room
Create a new room for a hotel.

**Endpoint:** `POST /api/v1/rooms?hotelId={hotelId}`

**Request Body:**
```json
{
  "roomNumber": "101",
  "price": 150.00
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "roomNumber": "101",
  "price": 150.00,
  "hotelId": 1,
  "hotelName": "Grand Hotel"
}
```

### Get All Rooms
Retrieve all rooms.

**Endpoint:** `GET /api/v1/rooms`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "roomNumber": "101",
    "price": 150.00,
    "hotelId": 1,
    "hotelName": "Grand Hotel"
  }
]
```

## Bookings API

### Create Booking
Create a new booking.

**Endpoint:** `POST /api/v1/bookings`

**Request Body:**
```json
{
  "guestName": "John Doe",
  "checkInDate": "2026-04-01",
  "checkOutDate": "2026-04-05",
  "roomId": 1
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "guestName": "John Doe",
  "checkInDate": "2026-04-01",
  "checkOutDate": "2026-04-05",
  "roomId": 1,
  "roomNumber": "101"
}
```

### Get All Bookings
Retrieve all bookings.

**Endpoint:** `GET /api/v1/bookings`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "guestName": "John Doe",
    "checkInDate": "2026-04-01",
    "checkOutDate": "2026-04-05",
    "roomId": 1,
    "roomNumber": "101"
  }
]
```

## HTTP Status Codes

| Code | Description |
|------|-------------|
| 200 | OK - Request successful |
| 201 | Created - Resource created |
| 400 | Bad Request - Invalid input |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server error |

## Validation Rules

### Hotel
- `name`: Required, 2-100 characters
- `location`: Required

### Room
- `roomNumber`: Required
- `price`: Required, must be > 0
- `hotelId`: Required

### Booking
- `guestName`: Required
- `checkInDate`: Required
- `checkOutDate`: Required
- `roomId`: Required

## Error Examples

### Resource Not Found (404)
```json
{
  "timestamp": "2026-03-16T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Hotel not found with id: '999'",
  "path": "/api/v1/hotels/999"
}
```

### Validation Error (400)
```json
{
  "timestamp": "2026-03-16T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "validationErrors": {
    "name": "Hotel name is required"
  }
}
```

---

**Version:** 1.0.0  
**Last Updated:** 2026-03-16
