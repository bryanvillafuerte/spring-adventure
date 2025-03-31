package com.project.springadventure.appUser;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app-users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AppUser> createAppUser(@Valid @RequestBody AppUserDTO appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setEmail(appUserDto.getEmail());
        appUser.setType(appUserDto.getType());
        return ResponseEntity.ok(appUserService.createAppUser(appUser));
    }
}
