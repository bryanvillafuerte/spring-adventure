package com.project.springadventure.config;

import com.project.springadventure.appUser.AppUser;
import com.project.springadventure.appUser.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  @Bean
  CommandLineRunner init(AppUserRepository appUserRepository) {
    return args -> {
      if (appUserRepository.count() == 0) {
        appUserRepository.save(new AppUser("john.doe@example.com", "USER"));
        appUserRepository.save(new AppUser("jane.smith@example.com", "ADMIN"));
        appUserRepository.save(new AppUser("bob.wilson@example.com", "USER"));
        appUserRepository.save(new AppUser("alice.brown@example.com", "ADMIN"));
        appUserRepository.save(new AppUser("charlie.davis@example.com", "USER"));
      }
    };
  }

}
