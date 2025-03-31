package com.project.springadventure.appUser;

import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

}
