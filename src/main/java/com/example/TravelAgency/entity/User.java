package com.example.TravelAgency.entity;

import com.example.TravelAgency.enums.Discount;
import com.example.TravelAgency.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    private Role role;

    private String username;

    private String password;

    private String cnp;

    private String address;

    private String phone;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Discount discount;
}
