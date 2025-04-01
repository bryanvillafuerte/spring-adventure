package com.project.springadventure.appUser;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppUserDTO {

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;

  @NotNull(message = "Type is required")
  @Enumerated(EnumType.STRING)
  private AppUserType type;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AppUserType getType() {
    return type;
  }

  public void setType(AppUserType type) {
    this.type = type;
  }
}
