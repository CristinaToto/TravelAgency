package com.example.TravelAgency.dto;

import com.example.TravelAgency.enums.MealPlan;
import lombok.Data;

@Data
public class CreateHotelDto {

    private String hotelName;
    private String town;
    private Long numberOfRooms;
    private MealPlan mealPlan;
    private Double price;


}
