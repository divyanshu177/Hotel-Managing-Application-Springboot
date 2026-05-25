# Architecture Diagrams & Visual Guide

## 1. Layered Architecture Diagram

```
╔═══════════════════════════════════════════════════════════════════════════╗
║                          HTTP CLIENT / SWAGGER UI                         ║
╚═════════════════════════════════════════════════════════════════════════╝
                                    ↓
╔═══════════════════════════════════════════════════════════════════════════╗
║                       PRESENTATION/API LAYER (Controllers)               ║
║  ┌─────────────────┐  ┌──────────────────┐  ┌──────────────────────┐   ║
║  │ HotelController │  │ RoomController   │  │ BookingController    │   ║
║  │ (3 endpoints)   │  │ (2 endpoints)    │  │ (2 endpoints)        │   ║
║  └─────────────────┘  └──────────────────┘  └──────────────────────┘   ║
║                                                                         ║
║  Responsibilities:                                                     ║
║  • Handle HTTP requests/responses                                      ║
║  • Basic input validation (@Valid)                                     ║
║  • Call service methods                                                ║
║  • Return appropriate HTTP status codes                                ║
╚═════════════════════════════════════════════════════════════════════════╝
                                    ↓
╔═══════════════════════════════════════════════════════════════════════════╗
║                     SERVICE/BUSINESS LOGIC LAYER                         ║
║  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────────┐  ║
║  │ HotelService     │  │ RoomService      │  │ BookingService       │  ║
║  │ (Interface)      │  │ (Interface)      │  │ (Interface)          │  ║
║  └────────┬─────────┘  └────────┬─────────┘  └──────────┬───────────┘  ║
║           │                     │                       │              ║
║  ┌────────▼─────────┐  ┌────────▼─────────┐  ┌────────▼───────────┐  ║
║  │ HotelServiceImpl  │  │ RoomServiceImpl   │  │BookingServiceImpl   │  ║
║  │ (Implementation) │  │ (Implementation) │  │(Implementation)    │  ║
║  └────────┬─────────┘  └────────┬─────────┘  └────────┬───────────┘  ║
║                                                        │              ║
║  Responsibilities:                                     │              ║
║  • Basic business logic                                │              ║
║  • Validation & error handling                         │              ║
║  • Service-level transactions (@Transactional)        │              ║
║  • DTO mapping (Entity ↔ DTO)                         │              ║
╚═════════════════════════════════════════════════════════════════════════╝
                                    ↓
╔═══════════════════════════════════════════════════════════════════════════╗
║                        PERSISTENCE/DATA ACCESS LAYER                     ║
║  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────────┐  ║
║  │ HotelRepository  │  │ RoomRepository   │  │ BookingRepository    │  ║
║  │ JpaRepository    │  │ JpaRepository    │  │ JpaRepository        │  ║
║  │ (Basic CRUD)     │  │ (Basic CRUD)     │  │ (Basic CRUD)         │  ║
║  └────────┬─────────┘  └────────┬─────────┘  └──────────┬───────────┘  ║
║           │                     │                       │              ║
║  Responsibilities:              │                       │              ║
║  • Basic CRUD operations        │                       │              ║
║  • Database operations          │                       │              ║
╚═════════════════════════════════════════════════════════════════════════╝
                                    ↓
╔═══════════════════════════════════════════════════════════════════════════╗
║                          ENTITY/DOMAIN LAYER                             ║
║  ┌─────────────────┐  ┌──────────────────┐  ┌──────────────────────┐   ║
║  │ Hotel Entity    │  │ Room Entity      │  │ Booking Entity       │   ║
║  │ - 3 fields      │  │ - 4 fields       │  │ - 5 fields           │   ║
║  │ - JPA mapping   │  │ - Relationships  │  │ - Relationships      │   ║
║  │ - Validation    │  │ - Validation     │  │ - Validation         │   ║
║  └─────────────────┘  └──────────────────┘  └──────────────────────┘   ║
║                                                                         ║
║  Relationships:                                                        ║
║  • Hotel (1) ──────→ (Many) Room                                       ║
║  • Hotel (1) ──────→ (Many) Booking (via Room)                         ║
║  • Room (1) ──────→ (Many) Booking                                     ║
╚═════════════════════════════════════════════════════════════════════════╝
                                    ↓
╔═══════════════════════════════════════════════════════════════════════════╗
║                            MYSQL DATABASE                                ║
║  ┌─────────────────┐  ┌──────────────────┐  ┌──────────────────────┐   ║
║  │ hotels table    │  │ rooms table       │  │ bookings table       │   ║
║  │ (3 columns)     │  │ (4 columns)       │  │ (5 columns)          │   ║
║  └─────────────────┘  └──────────────────┘  └──────────────────────┘   ║
║                                                                         ║
║  • Data persistence                                                    ║
║  • Data integrity & constraints                                        ║
║  • Foreign key relationships                                           ║
╚═════════════════════════════════════════════════════════════════════════╝
```

---

## 2. Entity Relationship Diagram (ERD)

```
                    ┌─────────────────────┐
                    │      HOTELS         │
                    ├─────────────────────┤
                    │ • id (PK)           │
                    │ • name              │
                    │ • location          │
                    └──────────┬──────────┘
                               │
                  ┌────────────┼────────────┐
                  │           │            │
              (1:M)        (1:M)         (1:M)
                  │           │            │
        ┌─────────▼────┐   ┌──▼──────────┐│
        │    ROOMS     │   │  BOOKINGS   ││
        ├──────────────┤   ├─────────────┤│
        │ • id (PK)    │   │ • id (PK)   ││
        │ • roomNumber │   │ • guestName ││
        │ • price      │   │ • checkIn   ││
        │ • hotel_id(FK)  │ • checkOut  ││
        └──────────────┘   │ • room_id(FK)│
                           └─────────────┘
                               │
                           (M:1)
                               │
                           (Points back to ROOMS)
```

---

## 3. Request-Response Flow

```
CLIENT REQUEST
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ HTTP REQUEST                                                    ║
║ Method: POST/GET                                                ║
║ URL: /api/v1/hotels/...                                        ║
║ Body: JSON (if POST)                                           ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ CONTROLLER LAYER (@RestController)                             ║
║ • Receives request                                              ║
║ • Validates input (@Valid)                                     ║
║ • Calls service method                                         ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ SERVICE LAYER (@Service)                                       ║
║ • Basic business logic                                         ║
║ • Validation                                                    ║
║ • Exception handling                                            ║
║ • DTO mapping                                                   ║
║ • Calls repository                                              ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ REPOSITORY LAYER (@Repository)                                 ║
║ • Database query                                                ║
║ • ORM mapping (Hibernate)                                       ║
║ • Returns entity/entities                                       ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ DATABASE                                                        ║
║ • SQL execution                                                 ║
║ • Data retrieval/update                                         ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ RESPONSE BACK TO SERVICE                                        ║
║ • Entity object(s) returned                                     ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ SERVICE CONVERTS TO DTO                                         ║
║ • Entity → DTO mapping                                          ║
║ • Returns DTO object(s)                                         ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ CONTROLLER RETURNS RESPONSE                                     ║
║ • Status code (200, 201, 204, 400, 404, etc.)                 ║
║ • Response body (DTO as JSON)                                   ║
╚═════════════════════════════════════════════════════════════════╝
    ↓
CLIENT RESPONSE (JSON)
    ↓
╔═════════════════════════════════════════════════════════════════╗
║ HTTP RESPONSE                                                   ║
║ Status: 200 OK / 201 Created / 400 Bad Request / 404 Not Found ║
║ Body: JSON response                                             ║
╚═════════════════════════════════════════════════════════════════╝
```

---

## 4. Data Flow - Create Hotel

```
┌──────────────────────────────────────────────────────────────────┐
│ CLIENT SENDS REQUEST                                             │
│ POST /api/v1/hotels                                              │
│ {                                                                │
│   "name": "Grand Hotel",                                         │
│   "location": "New York"                                         │
│ }                                                                │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ HotelController.createHotel(@Valid @RequestBody HotelDTO)        │
│ • Receives DTO                                                   │
│ • Validates @Valid annotations                                   │
│ • Calls hotelService.createHotel(hotelDTO)                       │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ HotelServiceImpl.createHotel(HotelDTO hotelDTO)                   │
│ • Maps DTO to Entity                                             │
│ • Creates Hotel entity object                                    │
│ • Calls hotelRepository.save(hotel)                              │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ HotelRepository.save(Hotel hotel)                                │
│ • Inherited from JpaRepository                                   │
│ • Converts entity to SQL INSERT statement                        │
│ • Executes: INSERT INTO hotels (...) VALUES (...)               │
│ • Returns saved entity with generated ID                         │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ DATABASE EXECUTION                                               │
│ SQL: INSERT INTO hotels                                          │
│   (name, location) VALUES ('Grand Hotel', 'New York')           │
│ Returns: Hotel with ID=1                                         │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ RESPONSE TO SERVICE                                              │
│ • Hotel entity with ID=1                                         │
│ • All fields populated from database                             │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ HotelServiceImpl.mapToDTO(Hotel hotel)                            │
│ • Converts entity back to DTO                                    │
│ • Maps all fields                                                │
│ • Returns HotelDTO object                                        │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ HotelController RESPONSE                                         │
│ • Wraps DTO in ResponseEntity                                    │
│ • Sets HTTP status 201 Created                                   │
│ • Jackson converts DTO to JSON                                   │
│ • Sends response to client                                       │
└──────────────────────────────────────────────────────────────────┘
        ↓
┌──────────────────────────────────────────────────────────────────┐
│ CLIENT RECEIVES RESPONSE                                         │
│ HTTP/1.1 201 Created                                             │
│ Content-Type: application/json                                   │
│                                                                  │
│ {                                                                │
│   "id": 1,                                                       │
│   "name": "Grand Hotel",                                         │
│   "location": "New York"                                         │
│ }                                                                │
└──────────────────────────────────────────────────────────────────┘
```

---

## 7. API Method Matrix

```
┌───────────────────────────────────────────────────────────────────┐
│                    HTTP METHODS & OPERATIONS                      │
├───────────────────────────────────────────────────────────────────┤
│ HTTP METHOD │ OPERATION │ IDEMPOTENT │ REQUEST BODY │ RESPONSE    │
├───────────────────────────────────────────────────────────────────┤
│ GET         │ Retrieve  │ Yes        │ None         │ 200 + Body  │
│             │           │            │              │ or 404      │
├───────────────────────────────────────────────────────────────────┤
│ POST        │ Create    │ No         │ Required     │ 201 + Body  │
│             │           │            │              │ or 400/404  │
├───────────────────────────────────────────────────────────────────┤
│ PUT         │ Update    │ Yes        │ Required     │ 200 + Body  │
│             │ (Full)    │            │ (All fields) │ or 404/400  │
├───────────────────────────────────────────────────────────────────┤
│ PATCH       │ Update    │ Yes        │ Optional     │ 204 No Body │
│             │ (Partial) │            │              │ or 404/400  │
├───────────────────────────────────────────────────────────────────┤
│ DELETE      │ Remove    │ Yes        │ None         │ 204 No Body │
│             │           │            │              │ or 404      │
└───────────────────────────────────────────────────────────────────┘
```

---

## 8. Status Code Decision Tree

```
REQUEST RECEIVED
    ↓
REQUEST VALID?
├─ NO → 400 Bad Request
│       (Validation failed)
│
└─ YES → RESOURCE EXISTS?
    ├─ NO → 404 Not Found
    │       (Hotel/Room/Booking not found)
    │
    └─ YES → OPERATION TYPE?
            ├─ GET → 200 OK (with body)
            ├─ POST → 201 Created (with body)
            └─ ERROR → 500 Internal Server Error
```

---

## 12. API Response Format

```
┌─────────────────────────────────────────────────────────────────┐
│                    SUCCESS RESPONSE (200/201)                   │
├─────────────────────────────────────────────────────────────────┤
│ HTTP Status: 200 OK / 201 Created                               │
│ Content-Type: application/json                                  │
│ Body: {                                                         │
│   "id": 1,                                                      │
│   "name": "value",                                              │
│   "field1": "value1",                                           │
│   "field2": "value2"                                            │
│ }                                                               │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                   ERROR RESPONSE (4xx/5xx)                      │
├─────────────────────────────────────────────────────────────────┤
│ HTTP Status: 400/404/500 (various error codes)                 │
│ Content-Type: application/json                                  │
│ Body: {                                                         │
│   "timestamp": "2026-03-16T10:30:00",                          │
│   "status": 404,                                                │
│   "error": "Not Found",                                         │
│   "message": "Hotel not found with id: '999'",                 │
│   "path": "/api/v1/hotels/999"                                  │
│ }                                                               │
└─────────────────────────────────────────────────────────────────┘
```

---

**Basic Architecture Documentation Complete!**

For more details, refer to ARCHITECTURE.md, API_DOCUMENTATION.md, and GETTING_STARTED.md.
