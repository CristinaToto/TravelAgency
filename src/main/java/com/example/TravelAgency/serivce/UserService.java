package com.example.TravelAgency.serivce;

import com.example.TravelAgency.dto.CreareUserDto;
import com.example.TravelAgency.dto.UpdateUserDto;
import com.example.TravelAgency.entity.User;

import java.util.List;

public interface UserService {

    User getById(Long userId);

    List<User> getAll();

    User save(CreareUserDto creareUserDTO);

    User update(UpdateUserDto userDTO);

}
