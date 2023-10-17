package com.example.authservice.domain.dto;

import com.example.authservice.domain.enam.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AppUserDTO {
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;
}
