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

import static com.example.TravelAgency.enums.Discount.*;
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
        double price = calculatePrice(reservation, user, hotel);
        Reservation reservation1 = buildReservation(reservation, user, hotel, price);
        return reservationRepository.save(reservation1);
    }


    @Override
    @Transactional
    public Reservation updateReservation(Reservation reservationDTO) {
        Reservation reservation = reservationRepository.findById(reservationDTO.getReservationId()).orElseThrow(()
                -> new NotFound("Reservation doesn't exist"));
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
        return reservations.stream().mapToDouble(Reservation::getPrice).sum();
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
        return convertPriceInCurrency(reservation, price);
    }

    private double convertPriceInCurrency(Reservation reservation, double price) {
        Currency currency = reservation.getCurrency();
        if (currency.equals(Currency.USD)) {
            price = price * 1.05;
        }
        if (currency.equals(Currency.RON)) {
            price = price * 4.93;
        }
        return price;
    }

    public double calculatePriceWithDiscount(double hotelPrice, Discount discount) {
        if (discount == LEVEL1) {
            hotelPrice = hotelPrice * 0.9;
        } else if (discount == LEVEL2) {
            hotelPrice = hotelPrice * 0.85;
        } else if (discount == LEVEL3) {
            hotelPrice = hotelPrice * 0.8;
        }
        return hotelPrice;
    }

    private void verifyMandatoryFields(Reservation reservationDTO) {
        if (isNull(reservationDTO.getHotel())) {
            throw new IllegalArgumentException("Hotel cannot be found");
        }
        if (isNull(reservationDTO.getUser())) {
            throw new IllegalArgumentException("User cannot be found");
        }

    }

    private Reservation buildReservation(Reservation reservation, User user, Hotel hotel, double price) {
        return Reservation.builder()
                .hotel(hotel)
                .numberOfRooms(reservation.getNumberOfRooms())
                .numberOfPersons(reservation.getNumberOfPersons())
                .mealPlan(reservation.getMealPlan())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .currency(reservation.getCurrency())
                .user(user)
                .price(price)
                .reservationDate(reservation.getReservationDate())
                .extraPrice(reservation.getExtraPrice())
                .build();
    }

}
