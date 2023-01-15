package com.example.TravelAgency.controller;

import com.example.TravelAgency.entity.Reservation;
import com.example.TravelAgency.serivce.impl.ReservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class ReservationController {
    private final ReservationServiceImpl reservationService;


    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public String getAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "index";
    }


    @GetMapping("/reservation/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(Long.valueOf(id)));
        return "reservation/show";
    }

    @GetMapping("/reservation/{reservationId}/delete")
    public String deleteReservation(@PathVariable String reservationId) {
        reservationService.deleteReservationById(Long.valueOf(reservationId));
        return "redirect:/";
    }

    @PostMapping("/save/{userId}/{hotelId}")
    public Reservation createReservation(@RequestBody Reservation reservation,
                                         @PathVariable("userId")Long userId,
                                         @PathVariable("hotelId")Long hotelId) {
        return reservationService.createReservation(reservation, userId, hotelId);
    }

    @PostMapping("/reservation/update/{id}")
    public String updateReservation(@ModelAttribute("reservation") Reservation command, @PathVariable String id) {
        command.setReservationId(Long.valueOf(id));
        Reservation savedCommand = reservationService.updateReservation(command);
        return "redirect:/reservation/" + savedCommand.getReservationId() + "/show";
    }

    @GetMapping("reservation/{id}/update")
    public String updateRes(@PathVariable String id, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(Long.valueOf(id)));
        return "reservation/reservationUpdate";
    }


    @GetMapping("/get/user/{userId}")
    public ResponseEntity<Set<Reservation>> getReservationsByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @GetMapping("/get/price/{userId}")
    public Double getTotalPriceForUser(@PathVariable("userId") Long userId) {
        return reservationService.getTotalPriceForUser(userId);
    }

    @GetMapping("/get/hotel/{hotelId}")
    public ResponseEntity<Set<Reservation>> getReservationByHotelId(@PathVariable Long hotelId){
        return ResponseEntity.ok(reservationService.getReservationsByHotelId(hotelId));
    }
}
