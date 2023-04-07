package com.example.TravelAgency.dto;

import com.example.TravelAgency.enums.MealPlan;
import lombok.Value;

@Value
public class CreateHotelDto {


    String hotelName;

    String town;

    Long numberOfRooms;

    MealPlan mealPlan;

    Double price;


}
