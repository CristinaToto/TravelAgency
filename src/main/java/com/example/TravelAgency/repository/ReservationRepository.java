package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Set<Reservation> findByUserUserId(Long userId);

    Set<Reservation> findByHotelHotelId(Long hotelId);
}
