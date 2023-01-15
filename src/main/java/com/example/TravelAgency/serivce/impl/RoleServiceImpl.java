package com.example.TravelAgency.serivce.impl;

import com.example.TravelAgency.entity.Role;
import com.example.TravelAgency.exception.NotFound;
import com.example.TravelAgency.repository.RoleRepository;
import com.example.TravelAgency.serivce.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new NotFound("Role id cannot be blank"));
    }
}
