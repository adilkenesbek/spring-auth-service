package com.example.authservice.security.service;

import com.example.authservice.security.dto.JwtAuthenticationResponse;
import com.example.authservice.security.dto.LoginRequest;
import com.example.authservice.security.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request);

    ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request);
}
