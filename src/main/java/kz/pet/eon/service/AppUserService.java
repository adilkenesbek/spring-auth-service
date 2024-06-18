package kz.pet.eon.service;

import kz.pet.eon.domain.dto.AppUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService {
    UserDetailsService userDetailsService();
    ResponseEntity<List<AppUserDTO>> getAll();
}
