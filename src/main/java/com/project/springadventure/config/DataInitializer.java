package com.project.springadventure.config;

import com.project.springadventure.appUser.AppUser;
import com.project.springadventure.appUser.AppUserRepository;
import com.project.springadventure.appUser.AppUserType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  @Bean
  CommandLineRunner init(AppUserRepository appUserRepository) {
    return args -> {
      if (appUserRepository.count() == 0) {
        AppUser user1 = new AppUser();
        user1.setEmail("john.doe@example.com");
        user1.setType(AppUserType.USER);
        appUserRepository.save(user1);

        AppUser user2 = new AppUser();
        user2.setEmail("jane.smith@example.com");
        user2.setType(AppUserType.ADMIN);
        appUserRepository.save(user2);

        AppUser user3 = new AppUser();
        user3.setEmail("bob.wilson@example.com");
        user3.setType(AppUserType.USER);
        appUserRepository.save(user3);

        AppUser user4 = new AppUser();
        user4.setEmail("alice.brown@example.com");
        user4.setType(AppUserType.ADMIN);
        appUserRepository.save(user4);

        AppUser user5 = new AppUser();
        user5.setEmail("charlie.davis@example.com");
        user5.setType(AppUserType.USER);
        appUserRepository.save(user5);
      }
    };
  }

}
