package kz.pet.eon.security.dto;

import kz.pet.eon.domain.dto.AppUserDTO;
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
