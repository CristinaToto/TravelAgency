package com.example.TravelAgency.serivce;

import com.example.TravelAgency.entity.Reservation;

import java.util.List;
import java.util.Set;

public interface ReservationService {
    Reservation createReservation(Reservation reservation, Long userId, Long hotelId);

    Reservation updateReservation(Reservation reservationDTO);

    void deleteReservationById(Long reservationId);

    Set<Reservation> getReservationsByUserId(Long userId);

    Double getTotalPriceForUser(Long userId);

    List<Reservation> getAllReservations();

    Set<Reservation> getReservationsByHotelId(Long hotelId);

     Reservation findReservationById(Long reservationId);
}
