package com.example.TravelAgency.serivce;

import com.example.TravelAgency.dto.CreateHotelDto;
import com.example.TravelAgency.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(CreateHotelDto hotel);

    List<Hotel> getAll();

    Hotel getById(Long hotelId);
}
