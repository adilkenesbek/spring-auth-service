package kz.pet.agato.service.impl;

import kz.pet.agato.domain.dto.AppUserDTO;
import kz.pet.agato.domain.mapper.AppUserMapper;
import kz.pet.agato.repository.AppUserRepository;
import kz.pet.agato.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> appUserRepository.findByEmail(username).orElseThrow(
                () -> new IllegalArgumentException("User not found!")
        );
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAll() {
        return ResponseEntity.ok(appUserMapper.toDto(appUserRepository.findAll()));
    }
}
