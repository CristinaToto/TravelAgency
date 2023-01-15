package com.example.TravelAgency.serivce.impl;

import com.example.TravelAgency.dto.CreateHotelDto;
import com.example.TravelAgency.entity.Hotel;
import com.example.TravelAgency.exception.NotFound;
import com.example.TravelAgency.repository.HotelRepository;
import com.example.TravelAgency.serivce.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new NotFound("Hotel not found"));
    }

    @Override
    @Transactional
    public Hotel saveHotel(CreateHotelDto hotel) {
        verifyHotelData(hotel);
        Hotel hotelToSave = new Hotel();
        hotelToSave.setHotelName(hotel.getHotelName());
        hotelToSave.setTown(hotel.getTown());
        hotelToSave.setNumberOfRooms(hotel.getNumberOfRooms());
        hotelToSave.setMealPlan(hotel.getMealPlan());
        hotelToSave.setPrice(hotel.getPrice());
        return hotelRepository.save(hotelToSave);
    }

    public void verifyHotelData(CreateHotelDto hotel) {
        if (isNull(hotel.getHotelName()) || hotel.getHotelName().isBlank()) {
            throw new IllegalArgumentException("Hotel name is mandatory!");
        }
    }
}
