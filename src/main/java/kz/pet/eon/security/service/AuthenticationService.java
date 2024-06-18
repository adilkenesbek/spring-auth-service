package kz.pet.eon.security.service;

import kz.pet.eon.security.dto.JwtAuthenticationResponse;
import kz.pet.eon.security.dto.LoginRequest;
import kz.pet.eon.security.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationService {
    ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;

    ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
