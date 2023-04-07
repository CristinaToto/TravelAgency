package com.example.TravelAgency.serivce.impl;

import com.example.TravelAgency.dto.CreareUserDto;
import com.example.TravelAgency.dto.UpdateUserDto;
import com.example.TravelAgency.entity.Role;
import com.example.TravelAgency.entity.User;
import com.example.TravelAgency.exception.InvalidFormat;
import com.example.TravelAgency.exception.NotFound;
import com.example.TravelAgency.repository.RoleRepository;
import com.example.TravelAgency.repository.UserRepository;
import com.example.TravelAgency.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.TravelAgency.util.UtilMethods.generatePassword;
import static com.example.TravelAgency.util.UtilMethods.verifyEmail;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new NotFound("User id not found");
    }

    @Override
    @Transactional
    public User save(CreareUserDto userDto) {
        Long roleId = userDto.getRoleId();
        if (isNull(roleId)) {
            log.error("");
            throw new IllegalArgumentException("Role id cannot be blank");
        }
        verifyCNP(userDto.getCnp());

        Role role = roleRepository
                .findById(userDto.getRoleId())
                .orElseThrow(() -> new NotFound("Role not found!"));

        User user = createUser(userDto, role);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UpdateUserDto userDto) {
        User user = getById(userDto.getId());
        verifyUserData(userDto);
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setUsername(userDto.getUsername());
        return userRepository.save(user);
    }

    private void verifyUserData(UpdateUserDto userDTO) {
        if (isNull(userDTO.getUsername()) || isNull(userDTO.getAddress()) || isNull(userDTO.getPhone())) {
            throw new IllegalArgumentException("Mandatory fields are empty");
        }
        if (!verifyEmail(userDTO.getEmail())) {
            throw new InvalidFormat("Invalid email address!");
        }
    }


    private User createUser(CreareUserDto userDto, Role role) {
        User user = User.builder()
                .role(role)
                .password(generatePassword())
                .username(userDto.getUsername())
                .address(userDto.getAddress())
                .phone(userDto.getPhone())
                .discount(userDto.getDiscount())
                .gender(userDto.getGender())
                .build();
        return user;
    }

    private void verifyCNP(String cnp) {
        if (cnp.length() != 13 && !hasDigits(cnp)) {
            throw new InvalidFormat("CNP doesn't have the correct format");
        }
    }

    private boolean hasDigits(String cnp) {
        for (int i = 0; i < cnp.length(); i++) {
            if (!Character.isDigit(cnp.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
