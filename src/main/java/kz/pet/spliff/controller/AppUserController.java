package kz.pet.agato.controller;

import kz.pet.agato.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return appUserService.getAll();
    }
}
