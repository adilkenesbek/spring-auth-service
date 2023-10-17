package com.example.authservice.service;

import com.example.authservice.domain.AppUserEntity;
import com.example.authservice.domain.dto.AppUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService {
    UserDetailsService userDetailsService();
    ResponseEntity<List<AppUserDTO>> getAll();
}
