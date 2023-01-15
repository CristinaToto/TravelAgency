package com.example.TravelAgency.h2database;

import com.example.TravelAgency.entity.Hotel;
import com.example.TravelAgency.entity.Reservation;
import com.example.TravelAgency.entity.Role;
import com.example.TravelAgency.entity.User;
import com.example.TravelAgency.enums.Discount;
import com.example.TravelAgency.enums.Gender;
import com.example.TravelAgency.enums.MealPlan;
import com.example.TravelAgency.enums.RoleType;
import com.example.TravelAgency.exception.NotFound;
import com.example.TravelAgency.repository.HotelRepository;
import com.example.TravelAgency.repository.ReservationRepository;
import com.example.TravelAgency.repository.RoleRepository;
import com.example.TravelAgency.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.example.TravelAgency.util.UtilMethods.generatePassword;

@Component
@AllArgsConstructor
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleRepository.saveAll(addRoles());
        userRepository.saveAll(addUsers());
        hotelRepository.saveAll(addHotels());
        reservationRepository.saveAll(addReservations());
    }

    public List<Role> addRoles() {

        Role admin = new Role();
        admin.setRoleType(RoleType.ADMIN);

        Role travelAgent = new Role();
        travelAgent.setRoleType(RoleType.TRAVEL_AGENT);

        Role client = new Role();
        client.setRoleType(RoleType.CLIENT);

        Role hotelEmployee = new Role();
        hotelEmployee.setRoleType(RoleType.HOTEL_EMPLOYEE);

        return List.of(admin, travelAgent, client, hotelEmployee);

    }

    public List<User> addUsers() {


        User user1 = new User();
        Role admin = roleRepository.findById(1L).orElseThrow(() -> new NotFound("Role with id 1 doesn't exist"));
        user1.setRole(admin);
        user1.setUsername("Adrian");
        user1.setPassword(generatePassword());
        user1.setCnp("1211310345345");
        user1.setAddress("Timisoara, bld. L. Rebreanu");
        user1.setPhone("0744478344");
        user1.setEmail("user1@gmail.com");
        user1.setGender(Gender.M);
        user1.setDiscount(Discount.LEVEL1);

        User user2 = new User();
        Role travelAgent = roleRepository.findById(2L).orElseThrow(() -> new NotFound("Role with id 2 doesn't exist"));
        user2.setRole(travelAgent);
        user2.setUsername("Oana");
        user2.setPassword(generatePassword());
        user2.setCnp("2211310345345");
        user2.setAddress("Craiova, str. Recunostintei");
        user2.setPhone("0734490344");
        user2.setEmail("user2@gmail.com");
        user2.setGender(Gender.F);
        user2.setDiscount(Discount.LEVEL2);

        User user3 = new User();
        Role client = roleRepository.findById(3L).orElseThrow(() -> new NotFound("Role with id 3 doesn't exist"));
        user3.setRole(client);
        user3.setUsername("Cristina");
        user3.setPassword(generatePassword());
        user3.setCnp("2211310000876");
        user3.setAddress("Bucuresti, Calea Victoriei");
        user3.setPhone("0768490144");
        user3.setEmail("user3@gmail.com");
        user3.setGender(Gender.F);
        user3.setDiscount(Discount.LEVEL3);


        User user4 = new User();
        Role hotelEmployee = roleRepository.findById(4L).orElseThrow(() -> new NotFound("Role with id 4 doesn't exist"));
        user4.setRole(hotelEmployee);
        user4.setUsername("Miruna");
        user4.setPassword(generatePassword());
        user4.setCnp("2211638960876");
        user4.setAddress("Bucuresti, str. Teilor");
        user4.setPhone("0755498944");
        user4.setEmail("user4@gmail.com");
        user4.setGender(Gender.F);
        user4.setDiscount(Discount.LEVEL3);

        return List.of(user1, user2, user3, user4);
    }

    public List<Hotel> addHotels() {

        Hotel continental = new Hotel();
        continental.setTown("Bucuresti");
        continental.setHotelName("Continental");
        continental.setNumberOfRooms(200L);
        continental.setMealPlan(MealPlan.ALL_INCLUSIVE);
        continental.setPrice(170.50);
        continental.setSpa(true);
        continental.setGym(true);
        continental.setTransferHotelToAirport(true);

        Hotel ibis = new Hotel();
        ibis.setTown("Viena");
        ibis.setHotelName("Ibis");
        ibis.setNumberOfRooms(120L);
        ibis.setMealPlan(MealPlan.BREAKFAST);
        ibis.setPrice(130.25);
        ibis.setSpa(false);
        ibis.setGym(true);
        ibis.setTransferHotelToAirport(true);

        Hotel sofitel = new Hotel();
        sofitel.setTown("Paris");
        sofitel.setHotelName("Sofitel");
        sofitel.setNumberOfRooms(220L);
        sofitel.setMealPlan(MealPlan.HALF_BOARD);
        sofitel.setPrice(200.99);
        sofitel.setSpa(true);
        sofitel.setGym(true);
        sofitel.setTransferHotelToAirport(true);

        return List.of(continental, ibis, sofitel);
    }


    public List<Reservation> addReservations() {

        Hotel continental = hotelRepository.findById(1L).orElseThrow(() -> new NotFound("Hotel with id 1 doesn't exist"));
        User user1 = userRepository.findById(1L).orElseThrow(() -> new NotFound("User with id 1 doesn't exist"));
        Hotel ibis = hotelRepository.findById(2L).orElseThrow(() -> new NotFound("Hotel with id 2 doesn't exist"));
        User user2 = userRepository.findById(2L).orElseThrow(() -> new NotFound("User with id 2 doesn't exist"));

        Reservation reservation1 = new Reservation();
        reservation1.setReservationId(1L);
        reservation1.setUser(user1);
        reservation1.setHotel(continental);
        reservation1.setNumberOfRooms(2);
        reservation1.setNumberOfPersons(3);
        reservation1.setPrice(200.00);
        reservation1.setMealPlan(MealPlan.ALL_INCLUSIVE);
        reservation1.setStartDate(LocalDate.parse("2022-11-22"));
        reservation1.setEndDate(LocalDate.parse("2022-11-25"));
        reservation1.setReservationDate(LocalDate.parse("2022-10-22"));
        reservation1.setExtraPrice(20.00);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationId(2L);
        reservation2.setUser(user2);
        reservation2.setHotel(ibis);
        reservation2.setNumberOfRooms(1);
        reservation2.setNumberOfPersons(2);
        reservation2.setPrice(150.00);
        reservation2.setMealPlan(MealPlan.BREAKFAST);
        reservation2.setStartDate(LocalDate.parse("2022-11-11"));
        reservation2.setEndDate(LocalDate.parse("2022-11-12"));
        reservation2.setReservationDate(LocalDate.parse("2022-10-22"));
        reservation2.setExtraPrice(0.00);

        return List.of(reservation1, reservation2);

    }


}
