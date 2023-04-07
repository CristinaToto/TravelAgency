package com.example.TravelAgency.controller;

import com.example.TravelAgency.dto.CreateHotelDto;
import com.example.TravelAgency.serivce.impl.HotelServiceImpl;
import com.example.TravelAgency.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HotelController {
    private final HotelServiceImpl hotelService;


    @GetMapping("/getAllHotels")
    public String getHotelById(Model model) {
        model.addAttribute("hotels", hotelService.getAll());
        return "hotel/show";
    }

    @PostMapping("/save")
    public Hotel createHotel(@RequestBody CreateHotelDto hotel) {
        return hotelService.saveHotel(hotel);
    }

    @GetMapping("/hotel/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("hotel", hotelService.getById(Long.valueOf(id)));
        return "hotel/show";
    }

}
