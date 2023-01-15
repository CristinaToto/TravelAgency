package com.example.TravelAgency.controller;

import com.example.TravelAgency.serivce.impl.ReservationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {

    private final ReservationServiceImpl reservationService;

    public IndexController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "index";
    }
}
