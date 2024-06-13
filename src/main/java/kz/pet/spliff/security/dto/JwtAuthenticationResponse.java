package kz.pet.agato.security.dto;

import kz.pet.agato.domain.dto.AppUserDTO;
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
