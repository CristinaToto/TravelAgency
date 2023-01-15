package com.example.TravelAgency.dto;

import com.example.TravelAgency.enums.MealPlan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Long reservationId;
    private Integer numberOfRooms;
    private Integer numberOfPersons;
    private Double price;
    private MealPlan mealPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double extraPrice;
}
