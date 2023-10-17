package com.example.authservice.security.dto;

import com.example.authservice.domain.AppUserEntity;
import com.example.authservice.domain.dto.AppUserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtAuthenticationResponse {
    private AppUserDTO user;
    private String token;
}
