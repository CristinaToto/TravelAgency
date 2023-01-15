package com.example.TravelAgency.controller;

import com.example.TravelAgency.serivce.impl.RoleServiceImpl;
import com.example.TravelAgency.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoleController {
    private final RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get/{roleId}")
    public Role getRoleById(@PathVariable("roleId") Long roleId) {
        return roleService.getRoleById(roleId);
    }
}
