package com.example.TravelAgency.controller;

import com.example.TravelAgency.dto.CreareUserDto;
import com.example.TravelAgency.dto.UpdateUserDto;
import com.example.TravelAgency.entity.User;
import com.example.TravelAgency.serivce.UserService;
import com.example.TravelAgency.serivce.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "listOfUsers";
    }

    @GetMapping("user/{id}/update")
    public String updateUser(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(Long.valueOf(id)));
        return "user/updateUser";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @GetMapping("/user/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(Long.valueOf(id)));
        return "user/show";
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody CreareUserDto user) {
        return userService.save(user);
    }

    @PostMapping("/user/update/{id}")
    public String update(@ModelAttribute("user") UpdateUserDto command, @PathVariable String id) {
        command.setId(Long.valueOf(id));
        User savedCommand = userService.update(command);
        return "redirect:/user/" + savedCommand.getUserId() + "/show";
    }

}
