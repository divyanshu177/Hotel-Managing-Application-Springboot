# Frontend - Hotel Booking Admin

This React app is connected to the Spring Boot backend endpoints:

- `GET/POST /api/v1/hotels`
- `GET/POST /api/v1/rooms`
- `GET/POST /api/v1/bookings`

## Prerequisites

- Node.js 20+
- Backend running on `http://localhost:8080`

## Install

```bash
npm install
```

## Run in development

```bash
npm run dev
```

App URL: `http://localhost:5173`

By default the app calls `/api/v1/*` and Vite proxies `/api` to `http://localhost:8080`.

## Optional environment override

Create `.env` using `.env.example` if backend is on a different host/port.

```bash
cp .env.example .env
```

Example value:

```env
VITE_API_BASE_URL=http://localhost:8080/api/v1
```

## Build

```bash
npm run build
```

## What the UI includes

- Create hotel form + hotels table
- Create room form (hotel dropdown) + rooms table
- Create booking form (room dropdown) + bookings table
