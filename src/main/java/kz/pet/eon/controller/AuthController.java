package kz.pet.eon.controller;

import jakarta.validation.Valid;
import kz.pet.eon.security.dto.JwtAuthenticationResponse;
import kz.pet.eon.security.dto.LoginRequest;
import kz.pet.eon.security.dto.SignUpRequest;
import kz.pet.eon.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody @Valid SignUpRequest signUpRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return authenticationService.signup(signUpRequest);
    }
}
