package com.example.TravelAgency.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private Long id;
    private String username;
    private String address;
    private String phone;
    private String email;

}
