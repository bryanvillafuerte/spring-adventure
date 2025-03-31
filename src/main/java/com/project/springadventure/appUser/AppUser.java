package com.project.springadventure.appUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "^(USER|ADMIN)$", message = "Type must be either 'USER' or 'ADMIN'")
    private String type;

    public AppUser(String email, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "appUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
