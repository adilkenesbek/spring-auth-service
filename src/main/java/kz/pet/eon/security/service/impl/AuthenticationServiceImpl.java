package kz.pet.eon.security.service.impl;

import kz.pet.eon.domain.AppUserEntity;
import kz.pet.eon.domain.RoleEntity;
import kz.pet.eon.domain.enam.Role;
import kz.pet.eon.domain.mapper.AppUserMapper;
import kz.pet.eon.handler.domain.ErrorCode;
import kz.pet.eon.handler.exception.UserAlreadyExistsException;
import kz.pet.eon.repository.AppUserRepository;
import kz.pet.eon.repository.RoleRepository;
import kz.pet.eon.security.dto.JwtAuthenticationResponse;
import kz.pet.eon.security.dto.LoginRequest;
import kz.pet.eon.security.dto.SignUpRequest;
import kz.pet.eon.security.service.AuthenticationService;
import kz.pet.eon.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.Set;

import static kz.pet.eon.utils.PasswordHashing.*;

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
    //TODO: проверка на существующего пользователя
    public ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Optional<AppUserEntity> existing = appUserRepository.findByUsername(request.getUsername());
        existing.ifPresent(user -> {
            throw new UserAlreadyExistsException(ErrorCode.ERROR_USER_ALREADY_EXISTS);
        });
        RoleEntity role = roleRepository.findByName(Role.ROLE_USER);
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(request.getPassword().toCharArray(), salt);

        AppUserEntity user = AppUserEntity.builder()
                .username(request.getUsername())
                .name(request.getName())
                .salt(toBase64(salt))
//                .password(passwordEncoder.encode(request.getPassword()))
                .password(hashedPassword)
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
    public ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        AppUserEntity user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(
                JwtAuthenticationResponse.builder()
                        .user(appUserMapper.toDto(user))
                        .token(jwt)
                        .build());
    }
}
