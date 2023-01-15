package com.example.TravelAgency.entity;

import com.example.TravelAgency.enums.Currency;
import com.example.TravelAgency.enums.MealPlan;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Hotel hotel;

    private Integer numberOfRooms;

    private Integer numberOfPersons;

    private Double price;

    @Enumerated(value = EnumType.STRING)
    private MealPlan mealPlan;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate reservationDate;

    private Double extraPrice;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
