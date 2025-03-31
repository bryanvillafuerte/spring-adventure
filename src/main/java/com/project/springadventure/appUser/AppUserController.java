package com.project.springadventure.appUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app-users")
@Tag(name = "App Users", description = "App User management API")
public class AppUserController {

  private final AppUserService appUserService;

  public AppUserController(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @PostMapping
  @Operation(summary = "Create a new App User", description = "Creates a new App User with the provided details and auto-generated ID.")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "App User created successfully",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AppUser.class),
                  examples = @ExampleObject(
                      value = """
                          {
                              "id": 10000,
                              "email": "appuser@example.com",
                              "type": "USER"
                          }
                          """
                  )
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid input data",
              content = @Content(
                  mediaType = "application/json",
                  examples = @ExampleObject(
                      value = """
                          {
                              "email": "Invalid email format",
                              "type": "Invalid user type. Allowed values are 'USER', 'ADMIN'."
                          }
                          """
                  )
              )
          )
      }
  )
  public ResponseEntity<AppUser> createAppUser(@Valid @RequestBody AppUserDTO appUserDto) {
    AppUser appUser = new AppUser();
    appUser.setEmail(appUserDto.getEmail());
    appUser.setType(appUserDto.getType());
    return ResponseEntity.ok(appUserService.createAppUser(appUser));
  }
}
