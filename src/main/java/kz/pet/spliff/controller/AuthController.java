package kz.pet.agato.controller;

import kz.pet.agato.security.dto.JwtAuthenticationResponse;
import kz.pet.agato.security.dto.LoginRequest;
import kz.pet.agato.security.dto.SignUpRequest;
import kz.pet.agato.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signup(signUpRequest);
    }
}
