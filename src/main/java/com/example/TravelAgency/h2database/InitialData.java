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
        Role admin = roleRepository.findById(1L).orElseThrow(() -> new NotFound("Role with id 1 doesn't exist"));
        User user1 = User.builder()
                .role(admin)
                .username("Adrian")
                .password(generatePassword())
                .cnp("1211310345345")
                .address("Timisoara, bld. L. Rebreanu")
                .phone("0744478344")
                .email("user1@gmail.com")
                .gender(Gender.M)
                .discount(Discount.LEVEL1)
                .build();


        Role travelAgent = roleRepository.findById(2L).orElseThrow(() -> new NotFound("Role with id 2 doesn't exist"));
        User user2 = User.builder()
                .role(travelAgent)
                .username("Oana")
                .password(generatePassword())
                .cnp("2211310345345")
                .address("Craiova, str. Recunostintei")
                .phone("0734490344")
                .email("user2@gmail.com")
                .gender(Gender.F)
                .discount(Discount.LEVEL2)
                .build();


        Role client = roleRepository.findById(3L).orElseThrow(() -> new NotFound("Role with id 3 doesn't exist"));
        User user3 = User.builder()
                .role(client)
                .username("Cristina")
                .password(generatePassword())
                .cnp("2211310345345")
                .address("Bucuresti, Calea Victoriei")
                .phone("0768490144")
                .email("user3@gmail.com")
                .gender(Gender.F)
                .discount(Discount.LEVEL3)
                .build();


        Role hotelEmployee = roleRepository.findById(4L).orElseThrow(() -> new NotFound("Role with id 4 doesn't exist"));
        User user4 = User.builder()
                .role(hotelEmployee)
                .username("Miruna")
                .password(generatePassword())
                .cnp("2011638960876")
                .address("Bucuresti, str. Teilor")
                .phone("0755498944")
                .email("user4@gmail.com")
                .gender(Gender.F)
                .discount(Discount.LEVEL3)
                .build();

        return List.of(user1, user2, user3, user4);
    }

    public List<Hotel> addHotels() {

        Hotel continental = Hotel.builder()
                .town("Bucuresti")
                .hotelName("Continental")
                .numberOfRooms(200L)
                .mealPlan(MealPlan.ALL_INCLUSIVE)
                .price(170.5)
                .spa(true)
                .gym(true)
                .transferHotelToAirport(true)
                .build();

        Hotel ibis = Hotel.builder()
                .town("Viena")
                .hotelName("Ibis")
                .numberOfRooms(120L)
                .mealPlan(MealPlan.BREAKFAST)
                .price(130.25)
                .spa(false)
                .gym(true)
                .transferHotelToAirport(true)
                .build();

        Hotel sofitel = Hotel.builder()
                .town("Paris")
                .hotelName("Sofitel")
                .numberOfRooms(220L)
                .mealPlan(MealPlan.HALF_BOARD)
                .price(200.99)
                .spa(true)
                .gym(true)
                .transferHotelToAirport(true)
                .build();

        return List.of(continental, ibis, sofitel);
    }


    public List<Reservation> addReservations() {

        Hotel continental = hotelRepository.findById(1L).orElseThrow(() -> new NotFound("Hotel with id 1 doesn't exist"));
        User user1 = userRepository.findById(1L).orElseThrow(() -> new NotFound("User with id 1 doesn't exist"));
        Hotel ibis = hotelRepository.findById(2L).orElseThrow(() -> new NotFound("Hotel with id 2 doesn't exist"));
        User user2 = userRepository.findById(2L).orElseThrow(() -> new NotFound("User with id 2 doesn't exist"));

        Reservation reservation1 = Reservation.builder()
                .reservationId(1L)
                .user(user1)
                .hotel(continental)
                .numberOfRooms(2)
                .numberOfPersons(3)
                .price(200.00)
                .mealPlan(MealPlan.ALL_INCLUSIVE)
                .startDate(LocalDate.parse("2022-11-22"))
                .endDate(LocalDate.parse("2022-11-25"))
                .reservationDate(LocalDate.parse("2022-10-22"))
                .extraPrice(20.00)
                .build();


        Reservation reservation2 = Reservation.builder()
                .reservationId(2L)
                .user(user2)
                .hotel(ibis)
                .numberOfRooms(1)
                .numberOfPersons(2)
                .price(150.00)
                .mealPlan(MealPlan.BREAKFAST)
                .startDate(LocalDate.parse("2022-11-11"))
                .endDate(LocalDate.parse("2022-11-12"))
                .reservationDate(LocalDate.parse("2022-10-22"))
                .extraPrice(0.00)
                .build();

        return List.of(reservation1, reservation2);

    }


}
