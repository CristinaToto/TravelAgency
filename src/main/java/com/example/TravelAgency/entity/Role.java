package com.example.TravelAgency.entity;

import com.example.TravelAgency.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;
}
