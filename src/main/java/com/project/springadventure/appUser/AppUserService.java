package com.project.springadventure.appUser;

import com.project.springadventure.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

  private final AppUserRepository appUserRepository;

  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  /**
   * Creates a new App User with the provided details and auto-generated ID starting from 10000.
   *
   * @param appUser DTO containing the details of the App User to be created
   * @return The created App User
   */
  public AppUser createAppUser(AppUser appUser) {
    return appUserRepository.save(appUser);
  }

  /**
   * Retrieves all App Users with pagination.
   *
   * @param pageRequest PageRequest object containing pagination information
   * @return A page of App Users
   */
  public Page<AppUser> getAllAppUsers(PageRequest pageRequest) {
    return appUserRepository.findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize()));
  }

  /**
   * Retrieves an App User by ID.
   *
   * @param id The ID of the App User to retrieve
   * @return The App User with the specified ID
   * @throws UserNotFoundException if no App User with the specified ID is found
   */
  public AppUser getAppUserById(Long id) {
    return appUserRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  /**
   * Retrieves and filters App Users by type.
   * @param type The type of App Users to filter by (e.g., "USER", "ADMIN")
   * @return A list of App Users matching the specified type
   */
  public List<AppUser> getAppUsersByType(String type) {
    if (type == null || type.isEmpty()) {
      return appUserRepository.findAll();
    }
    try {
      AppUserType userType = AppUserType.valueOf(type.toUpperCase());
      return appUserRepository.findByType(userType);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid user type: " + type);
    }
  }

}
