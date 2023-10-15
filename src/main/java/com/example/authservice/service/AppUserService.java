package com.example.authservice.service;

import com.example.authservice.domain.AppUserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService {
    UserDetailsService userDetailsService();
    ResponseEntity<List<AppUserEntity>> getAll();
}
