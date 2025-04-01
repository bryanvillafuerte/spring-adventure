package com.project.springadventure.appUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  @SequenceGenerator(
      name = "user_sequence",
      sequenceName = "user_sequence",
      initialValue = 10000,
      allocationSize = 1
  )
  private Long id;

  @NotEmpty(message = "Email is required.")
  @NotBlank(message = "Email is required.")
  @Email(message = "Invalid email format.")
  private String email;

  @NotNull(message = "Type is required")
  @Enumerated(EnumType.STRING)
  private AppUserType type;

  public AppUser(Long id, String email, AppUserType type) {
    this.id = id;
    this.email = email;
    this.type = type;
  }

  public AppUser() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  @Override
  public String toString() {
    return "AppUser{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", type=" + type +
        '}';
  }
}
