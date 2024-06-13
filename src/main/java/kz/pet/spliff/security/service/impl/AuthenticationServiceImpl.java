package kz.pet.agato.security.service.impl;

import kz.pet.agato.domain.AppUserEntity;
import kz.pet.agato.domain.RoleEntity;
import kz.pet.agato.domain.enam.Role;
import kz.pet.agato.domain.mapper.AppUserMapper;
import kz.pet.agato.repository.AppUserRepository;
import kz.pet.agato.repository.RoleRepository;
import kz.pet.agato.security.dto.JwtAuthenticationResponse;
import kz.pet.agato.security.dto.LoginRequest;
import kz.pet.agato.security.dto.SignUpRequest;
import kz.pet.agato.security.service.AuthenticationService;
import kz.pet.agato.security.service.JwtService;
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
    private final AppUserMapper appUserMapper;

    @Override
    public ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request) {
        RoleEntity role = roleRepository.findByName(Role.USER);

        AppUserEntity user = AppUserEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .isBlocked(false)
                .build();
        user = appUserRepository.saveAndFlush(user);

        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(
                JwtAuthenticationResponse.builder()
                        .user(appUserMapper.toDto(user))
                        .token(jwt)
                        .build());
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        AppUserEntity user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(
                JwtAuthenticationResponse.builder()
                        .user(appUserMapper.toDto(user))
                        .token(jwt)
                        .build());
    }
}
