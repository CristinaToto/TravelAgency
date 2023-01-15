package com.example.TravelAgency.dto;

import com.example.TravelAgency.enums.Discount;
import com.example.TravelAgency.enums.Gender;
import lombok.Data;

@Data
public class CreareUserDto {

    private String username;
    private Long roleId;
    private String address;
    private String phone;
    private String email;
    private String cnp;
    private String password;
    private Gender gender;
    private Discount discount;
}
