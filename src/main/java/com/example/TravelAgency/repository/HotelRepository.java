package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
