package com.example.TravelAgency.entity;

import com.example.TravelAgency.enums.MealPlan;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "hotels")
@AllArgsConstructor
public class Hotel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @Lob
    private byte[] photo;

    private String hotelName;

    private String town;

    private Long numberOfRooms;

    @Enumerated(value = EnumType.STRING)
    private MealPlan mealPlan;

    private Double price;

    private boolean spa;

    private boolean gym;

    private boolean transferHotelToAirport;

    public Hotel() {
    }

}
