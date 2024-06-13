package kz.pet.spliff.security.service;

import kz.pet.spliff.security.dto.JwtAuthenticationResponse;
import kz.pet.spliff.security.dto.LoginRequest;
import kz.pet.spliff.security.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationService {
    ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;

    ResponseEntity<JwtAuthenticationResponse> login(LoginRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
