import { useEffect, useMemo, useState } from 'react'
import './App.css'

const apiRoot = (import.meta.env.VITE_API_BASE_URL || '/api/v1').replace(/\/$/, '')

async function request(path, options = {}) {
  const response = await fetch(`${apiRoot}${path}`, {
    headers: {
      'Content-Type': 'application/json',
      ...(options.headers || {}),
    },
    ...options,
  })

  if (!response.ok) {
    let message = `Request failed with status ${response.status}`
    try {
      const data = await response.json()
      message = data.message || data.error || message
    } catch {
      // Ignore JSON parsing errors and use the default message.
    }
    throw new Error(message)
  }

  if (response.status === 204) {
    return null
  }

  return response.json()
}

function App() {
  const [hotels, setHotels] = useState([])
  const [rooms, setRooms] = useState([])
  const [bookings, setBookings] = useState([])

  const [hotelForm, setHotelForm] = useState({ name: '', location: '' })
  const [roomForm, setRoomForm] = useState({ roomNumber: '', price: '', hotelId: '' })
  const [bookingForm, setBookingForm] = useState({
    guestName: '',
    checkInDate: '',
    checkOutDate: '',
    roomId: '',
  })

  const [loading, setLoading] = useState(true)
  const [submitting, setSubmitting] = useState(false)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  const sortedHotels = useMemo(
    () => [...hotels].sort((a, b) => Number(a.id) - Number(b.id)),
    [hotels],
  )

  const sortedRooms = useMemo(
    () => [...rooms].sort((a, b) => Number(a.id) - Number(b.id)),
    [rooms],
  )

  const sortedBookings = useMemo(
    () => [...bookings].sort((a, b) => Number(a.id) - Number(b.id)),
    [bookings],
  )

  useEffect(() => {
    loadData()
  }, [])

  async function loadData() {
    setLoading(true)
    setError('')

    try {
      const [hotelsData, roomsData, bookingsData] = await Promise.all([
        request('/hotels'),
        request('/rooms'),
        request('/bookings'),
      ])

      setHotels(hotelsData || [])
      setRooms(roomsData || [])
      setBookings(bookingsData || [])
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  function clearFeedback() {
    setError('')
    setSuccess('')
  }

  async function handleHotelSubmit(event) {
    event.preventDefault()
    clearFeedback()
    setSubmitting(true)

    try {
      await request('/hotels', {
        method: 'POST',
        body: JSON.stringify(hotelForm),
      })

      setHotelForm({ name: '', location: '' })
      setSuccess('Hotel created successfully.')
      await loadData()
    } catch (err) {
      setError(err.message)
    } finally {
      setSubmitting(false)
    }
  }

  async function handleRoomSubmit(event) {
    event.preventDefault()
    clearFeedback()
    setSubmitting(true)

    try {
      await request(`/rooms?hotelId=${encodeURIComponent(roomForm.hotelId)}`, {
        method: 'POST',
        body: JSON.stringify({
          roomNumber: roomForm.roomNumber,
          price: Number(roomForm.price),
          hotelId: Number(roomForm.hotelId),
        }),
      })

      setRoomForm({ roomNumber: '', price: '', hotelId: '' })
      setSuccess('Room created successfully.')
      await loadData()
    } catch (err) {
      setError(err.message)
    } finally {
      setSubmitting(false)
    }
  }

  async function handleBookingSubmit(event) {
    event.preventDefault()
    clearFeedback()
    setSubmitting(true)

    try {
      await request('/bookings', {
        method: 'POST',
        body: JSON.stringify({
          guestName: bookingForm.guestName,
          checkInDate: bookingForm.checkInDate,
          checkOutDate: bookingForm.checkOutDate,
          roomId: Number(bookingForm.roomId),
        }),
      })

      setBookingForm({
        guestName: '',
        checkInDate: '',
        checkOutDate: '',
        roomId: '',
      })
      setSuccess('Booking created successfully.')
      await loadData()
    } catch (err) {
      setError(err.message)
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <main className="page">
      <header className="header">
        <h1>Hotel Booking Admin</h1>
        <p>React app connected to Spring Boot endpoints under /api/v1</p>
      </header>

      <section className="status-row">
        {loading ? <p className="info">Loading data...</p> : null}
        {success ? <p className="success">{success}</p> : null}
        {error ? <p className="error">{error}</p> : null}
      </section>

      <section className="grid">
        <article className="card">
          <h2>Create Hotel</h2>
          <form onSubmit={handleHotelSubmit} className="form">
            <label>
              Name
              <input
                required
                value={hotelForm.name}
                onChange={(event) =>
                  setHotelForm((previous) => ({ ...previous, name: event.target.value }))
                }
              />
            </label>

            <label>
              Location
              <input
                required
                value={hotelForm.location}
                onChange={(event) =>
                  setHotelForm((previous) => ({ ...previous, location: event.target.value }))
                }
              />
            </label>

            <button disabled={submitting} type="submit">
              Add Hotel
            </button>
          </form>
        </article>

        <article className="card">
          <h2>Create Room</h2>
          <form onSubmit={handleRoomSubmit} className="form">
            <label>
              Room Number
              <input
                required
                value={roomForm.roomNumber}
                onChange={(event) =>
                  setRoomForm((previous) => ({ ...previous, roomNumber: event.target.value }))
                }
              />
            </label>

            <label>
              Price
              <input
                required
                min="0.01"
                step="0.01"
                type="number"
                value={roomForm.price}
                onChange={(event) =>
                  setRoomForm((previous) => ({ ...previous, price: event.target.value }))
                }
              />
            </label>

            <label>
              Hotel
              <select
                required
                value={roomForm.hotelId}
                onChange={(event) =>
                  setRoomForm((previous) => ({ ...previous, hotelId: event.target.value }))
                }
              >
                <option value="">Select a hotel</option>
                {sortedHotels.map((hotel) => (
                  <option key={hotel.id} value={hotel.id}>
                    {hotel.id} - {hotel.name}
                  </option>
                ))}
              </select>
            </label>

            <button disabled={submitting || sortedHotels.length === 0} type="submit">
              Add Room
            </button>
          </form>
        </article>

        <article className="card">
          <h2>Create Booking</h2>
          <form onSubmit={handleBookingSubmit} className="form">
            <label>
              Guest Name
              <input
                required
                value={bookingForm.guestName}
                onChange={(event) =>
                  setBookingForm((previous) => ({ ...previous, guestName: event.target.value }))
                }
              />
            </label>

            <label>
              Check-in
              <input
                required
                type="date"
                value={bookingForm.checkInDate}
                onChange={(event) =>
                  setBookingForm((previous) => ({ ...previous, checkInDate: event.target.value }))
                }
              />
            </label>

            <label>
              Check-out
              <input
                required
                type="date"
                value={bookingForm.checkOutDate}
                onChange={(event) =>
                  setBookingForm((previous) => ({ ...previous, checkOutDate: event.target.value }))
                }
              />
            </label>

            <label>
              Room
              <select
                required
                value={bookingForm.roomId}
                onChange={(event) =>
                  setBookingForm((previous) => ({ ...previous, roomId: event.target.value }))
                }
              >
                <option value="">Select a room</option>
                {sortedRooms.map((room) => (
                  <option key={room.id} value={room.id}>
                    {room.id} - Room {room.roomNumber} ({room.hotelName})
                  </option>
                ))}
              </select>
            </label>

            <button disabled={submitting || sortedRooms.length === 0} type="submit">
              Add Booking
            </button>
          </form>
        </article>
      </section>

      <section className="lists-grid">
        <article className="card">
          <h2>Hotels</h2>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Location</th>
                </tr>
              </thead>
              <tbody>
                {sortedHotels.length === 0 ? (
                  <tr>
                    <td colSpan="3">No hotels yet</td>
                  </tr>
                ) : (
                  sortedHotels.map((hotel) => (
                    <tr key={hotel.id}>
                      <td>{hotel.id}</td>
                      <td>{hotel.name}</td>
                      <td>{hotel.location}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </article>

        <article className="card">
          <h2>Rooms</h2>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Room</th>
                  <th>Price</th>
                  <th>Hotel</th>
                </tr>
              </thead>
              <tbody>
                {sortedRooms.length === 0 ? (
                  <tr>
                    <td colSpan="4">No rooms yet</td>
                  </tr>
                ) : (
                  sortedRooms.map((room) => (
                    <tr key={room.id}>
                      <td>{room.id}</td>
                      <td>{room.roomNumber}</td>
                      <td>{room.price}</td>
                      <td>{room.hotelName || room.hotelId}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </article>

        <article className="card">
          <h2>Bookings</h2>
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Guest</th>
                  <th>Room</th>
                  <th>Check-in</th>
                  <th>Check-out</th>
                </tr>
              </thead>
              <tbody>
                {sortedBookings.length === 0 ? (
                  <tr>
                    <td colSpan="5">No bookings yet</td>
                  </tr>
                ) : (
                  sortedBookings.map((booking) => (
                    <tr key={booking.id}>
                      <td>{booking.id}</td>
                      <td>{booking.guestName}</td>
                      <td>{booking.roomNumber || booking.roomId}</td>
                      <td>{booking.checkInDate}</td>
                      <td>{booking.checkOutDate}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </article>
      </section>
    </main>
  )
}

export default App
