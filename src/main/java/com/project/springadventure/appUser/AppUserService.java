package com.project.springadventure.appUser;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

  private final AppUserRepository appUserRepository;

  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  public AppUser createAppUser(AppUser appUser) {
    return appUserRepository.save(appUser);
  }

  public List<AppUser> getAllAppUsers() {
    return appUserRepository.findAll();
  }

  public AppUser getAppUserById(Long id) {
    return appUserRepository.findById(id).orElse(null);
  }

}
