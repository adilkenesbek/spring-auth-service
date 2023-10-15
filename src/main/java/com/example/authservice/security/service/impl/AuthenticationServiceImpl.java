package com.example.authservice.security.service.impl;

import com.example.authservice.domain.AppUserEntity;
import com.example.authservice.domain.RoleEntity;
import com.example.authservice.domain.enam.Role;
import com.example.authservice.repository.AppUserRepository;
import com.example.authservice.repository.RoleRepository;
import com.example.authservice.security.dto.JwtAuthenticationResponse;
import com.example.authservice.security.dto.LoginRequest;
import com.example.authservice.security.dto.SignUpRequest;
import com.example.authservice.security.service.AuthenticationService;
import com.example.authservice.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request) {
        RoleEntity role = roleRepository.findByName(Role.USER);

        var user = AppUserEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .isBlocked(false)
                .build();
        appUserRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(JwtAuthenticationResponse.builder().token(jwt).build());
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(JwtAuthenticationResponse.builder().token(jwt).build());
    }
}
