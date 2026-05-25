package com.dipdeveloper.spring_boot_hotel_booking.service.impl;

import com.dipdeveloper.spring_boot_hotel_booking.dto.RoomDTO;
import com.dipdeveloper.spring_boot_hotel_booking.entity.Hotel;
import com.dipdeveloper.spring_boot_hotel_booking.entity.Room;
import com.dipdeveloper.spring_boot_hotel_booking.exception.ResourceNotFoundException;
import com.dipdeveloper.spring_boot_hotel_booking.repository.HotelRepository;
import com.dipdeveloper.spring_boot_hotel_booking.repository.RoomRepository;
import com.dipdeveloper.spring_boot_hotel_booking.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public RoomDTO createRoom(Long hotelId, RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", hotelId));

        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setPrice(roomDTO.getPrice());
        room.setHotel(hotel);

        Room savedRoom = roomRepository.save(room);
        return mapToDTO(savedRoom);
    }

    @Override
    @Transactional(readOnly = true)
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        return mapToDTO(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoomDTO getRoomByRoomNumberAndHotelId(String roomNumber, Long hotelId) {
        Room room = roomRepository.findByRoomNumberAndHotelId(roomNumber, hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "roomNumber and hotelId", roomNumber + " and " + hotelId));
        return mapToDTO(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> getRoomsWithinPrice(Long hotelId, Double maxPrice) {
        return roomRepository.findByHotelIdAndPriceLessThanEqual(hotelId, maxPrice).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));

        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setPrice(roomDTO.getPrice());

        Room updatedRoom = roomRepository.save(room);
        return mapToDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Room", "id", id);
        }
        roomRepository.deleteById(id);
    }

    private RoomDTO mapToDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .price(room.getPrice())
                .hotelId(room.getHotel() != null ? room.getHotel().getId() : null)
                .hotelName(room.getHotel() != null ? room.getHotel().getName() : null)
                .build();
    }
}

