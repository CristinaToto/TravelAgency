package com.example.TravelAgency.serivce.impl;

import com.example.TravelAgency.entity.Hotel;
import com.example.TravelAgency.entity.Reservation;
import com.example.TravelAgency.entity.User;
import com.example.TravelAgency.enums.Currency;
import com.example.TravelAgency.enums.Discount;
import com.example.TravelAgency.exception.NotFound;
import com.example.TravelAgency.repository.HotelRepository;
import com.example.TravelAgency.repository.ReservationRepository;
import com.example.TravelAgency.repository.UserRepository;
import com.example.TravelAgency.serivce.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  HotelRepository hotelRepository,
                                  UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new NotFound("Reservation doesn't exist"));
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation, Long userId, Long hotelId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFound("User doesn't exist"));
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NotFound("Hotel doesn't exist"));
        Reservation reservation1 = new Reservation();
        double price = calculatePrice(reservation, user, hotel);
        reservation1.setHotel(hotel);
        reservation1.setNumberOfRooms(reservation.getNumberOfRooms());
        reservation1.setNumberOfPersons(reservation.getNumberOfPersons());
        reservation1.setMealPlan(reservation.getMealPlan());
        reservation1.setStartDate(reservation.getStartDate());
        reservation1.setEndDate(reservation.getEndDate());
        reservation1.setCurrency(reservation.getCurrency());
        reservation1.setUser(user);
        reservation1.setPrice(price);
        reservation1.setReservationDate(reservation.getReservationDate());
        reservation1.setExtraPrice(reservation.getExtraPrice());

        return reservationRepository.save(reservation1);
    }


    @Override
    @Transactional
    public Reservation updateReservation(Reservation reservationDTO) {
        Reservation reservation = reservationRepository.findById(reservationDTO.getReservationId()).orElseThrow(() -> new NotFound("Reservation doesn't exist"));
        verifyMandatoryFields(reservationDTO);

        reservation.setNumberOfRooms(reservationDTO.getNumberOfRooms());
        reservation.setNumberOfPersons(reservationDTO.getNumberOfPersons());
        reservation.setPrice(reservationDTO.getNumberOfRooms() * reservation.getHotel().getPrice());
        reservation.setMealPlan(reservationDTO.getMealPlan());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setExtraPrice(reservationDTO.getExtraPrice());

        return reservationRepository.save(reservation);
    }

    private void verifyMandatoryFields(Reservation reservationDTO) {
        if (isNull(reservationDTO.getHotel())) {
            throw new IllegalArgumentException("Hotel cannot be found");
        }
        if (isNull(reservationDTO.getUser())) {
            throw new IllegalArgumentException("User cannot be found");
        }

    }

    @Override
    public void deleteReservationById(Long reservationId) {
        Optional<Reservation> reservationDel = reservationRepository.findById(reservationId);
        if (reservationDel.isEmpty()) {
            throw new NotFound("Reservation not found");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Set<Reservation> getReservationsByUserId(Long userId) {
        Set<Reservation> reservations = new HashSet<>();
        reservationRepository.findByUserUserId(userId).iterator().forEachRemaining(reservations::add);
        return reservations;
    }

    @Override
    public Double getTotalPriceForUser(Long userId) {
        Set<Reservation> reservations = new HashSet<>(getReservationsByUserId(userId));
        Double totalPrice = 0.0;
        for (Reservation currentReservation : reservations) {
            totalPrice += currentReservation.getPrice();
        }
        return totalPrice;
    }

    @Override
    public Set<Reservation> getReservationsByHotelId(Long hotelId) {
        Set<Reservation> reservations = new HashSet<>();
        reservationRepository.findByHotelHotelId(hotelId).iterator().forEachRemaining(reservations::add);
        return reservations;
    }


    private double calculatePrice(Reservation reservation, User user, Hotel hotel) {
        long nights = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        double price = reservation.getNumberOfRooms()
                * calculatePriceWithDiscount(hotel.getPrice(), user.getDiscount()) * nights;

        if (reservation.getCurrency() != null && reservation.getCurrency().equals(Currency.USD)) {
            price = price * 1.05;
        }
        if (reservation.getCurrency() != null && reservation.getCurrency().equals(Currency.RON)) {
            price = price * 4.93;
        }
        return price;
    }

    public double calculatePriceWithDiscount(double hotelPrice, Discount discount) {
        double finalPrice = hotelPrice;
        if (discount == Discount.LEVEL1) {
            finalPrice = finalPrice * 0.9;
        } else if (discount == Discount.LEVEL2) {
            finalPrice = finalPrice * 0.85;
        } else if (discount == Discount.LEVEL3) {
            finalPrice = finalPrice * 0.8;
        }
        return finalPrice;
    }
}
