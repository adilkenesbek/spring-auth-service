package kz.pet.agato.security.service;

import kz.pet.agato.security.dto.JwtAuthenticationResponse;
import kz.pet.agato.security.dto.LoginRequest;
import kz.pet.agato.security.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request);

    ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request);
}
