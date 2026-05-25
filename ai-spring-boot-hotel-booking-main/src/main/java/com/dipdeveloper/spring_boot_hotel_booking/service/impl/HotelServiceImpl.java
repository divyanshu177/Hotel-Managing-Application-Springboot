package com.dipdeveloper.spring_boot_hotel_booking.service.impl;

import com.dipdeveloper.spring_boot_hotel_booking.dto.HotelDTO;
import com.dipdeveloper.spring_boot_hotel_booking.entity.Hotel;
import com.dipdeveloper.spring_boot_hotel_booking.exception.ResourceNotFoundException;
import com.dipdeveloper.spring_boot_hotel_booking.repository.HotelRepository;
import com.dipdeveloper.spring_boot_hotel_booking.service.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setLocation(hotelDTO.getLocation());

        Hotel savedHotel = hotelRepository.save(hotel);
        return mapToDTO(savedHotel);
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", id));
        return mapToDTO(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> getHotelsByLocation(String location) {
        return hotelRepository.findByLocation(location).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", id));

        hotel.setName(hotelDTO.getName());
        hotel.setLocation(hotelDTO.getLocation());

        Hotel updatedHotel = hotelRepository.save(hotel);
        return mapToDTO(updatedHotel);
    }

    @Override
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hotel", "id", id);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDTO getHotelByName(String name) {
        Hotel hotel = hotelRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "name", name));
        return mapToDTO(hotel);
    }

    private HotelDTO mapToDTO(Hotel hotel) {
        return HotelDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .build();
    }
}

