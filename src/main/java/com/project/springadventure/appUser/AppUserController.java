package com.project.springadventure.appUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app-users")
@Tag(name = "App Users", description = "App User management API")
public class AppUserController {

  private final AppUserService appUserService;

  public AppUserController(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  /**
   * Creates a new App User with the provided details and auto-generated ID starting from 10000.
   *
   * @param appUserDto DTO containing the details of the App User to be created
   * @return ResponseEntity containing the created App User
   */
  @PostMapping
  @Operation(summary = "Create a new App User", description = """
      Creates a new App User with the provided details and auto-generated ID starting from 10000.
      
      Curl example:
      ```bash
      curl -X POST http://localhost:8080/api/v1/app-users \\
        -H 'Content-Type: application/json' \\
        -d '{
          "email": "appuser@example.com",
          "type": "USER"
        }'
      ```
      """)
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "App User created successfully",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AppUser.class),
              examples = @ExampleObject(value = """
                  {
                      "id": 10000,
                      "email": "appuser@example.com",
                      "type": "USER"
                  }
                  """)
          )
      ),
      @ApiResponse(
          responseCode = "400",
          description = "Invalid input data",
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(value = """
                  {
                      "email": ["Email is required.", "Invalid email format."],
                      "type": ["Type is required.", "Invalid user type. Allowed values are 'USER', 'ADMIN'."]
                  }
                  """)
          )
      )
  })
  public ResponseEntity<AppUser> createAppUser(@Valid @RequestBody AppUserDTO appUserDto) {
    AppUser appUser = new AppUser();
    appUser.setEmail(appUserDto.getEmail());
    appUser.setType(appUserDto.getType());
    return ResponseEntity.ok(appUserService.createAppUser(appUser));
  }

  /**
   * Retrieves a paginated list of App Users.
   *
   * @param page Page number (0-based, defaults to 0)
   * @param size Number of items per page (defaults to 10)
   * @return ResponseEntity containing the paginated list of App Users
   */
  @GetMapping
  @Operation(summary = "Get all App Users", description = """
      Retrieves a paginated list of App Users.
      
      Parameters:
      - page: Page number (0-based, defaults to 0)
      - size: Number of items per page (defaults to 10)
      
      Curl examples:
      ```bash
      # Get first page with default size
      curl http://localhost:8080/api/v1/app-users
      
      # Get second page with 5 items per page
      curl 'http://localhost:8080/api/v1/app-users?page=1&size=5'
      ```
      """)
  @ApiResponse(
      responseCode = "200",
      description = "Paginated list of App Users",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = AppUser.class),
          examples = @ExampleObject(value = """
              {
                  "content": [
                      {
                          "id": 10000,
                          "email": "appuser1@example.com",
                          "type": "USER"
                      },
                      {
                          "id": 10001,
                          "email": "appuser2@example.com",
                          "type": "ADMIN"
                      }
                  ],
                  "pageable": {
                      "pageNumber": 0,
                      "pageSize": 10,
                      "sort": []
                  },
                  "totalElements": 2,
                  "totalPages": 1
              }
              """)
      )
  )
  public ResponseEntity<Page<AppUser>> getAllAppUsers(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(appUserService.getAllAppUsers(PageRequest.of(page, size)));
  }

  /**
   * Retrieves an App User by their ID.
   *
   * @param id ID of the App User to retrieve
   * @return ResponseEntity containing the App User if found, or 404 Not Found if not found
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get App User by ID", description = """
      Retrieves an App User by their ID.
      
      Curl example:
      ```bash
      curl http://localhost:8080/api/v1/app-users/10000
      ```
      """)
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "App User found",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AppUser.class),
              examples = @ExampleObject(value = """
                  {
                      "id": 10000,
                      "email": "appuser@example.com",
                      "type": "USER"
                  }
                  """)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          description = "App User not found",
          content = @Content
      )
  })
  public ResponseEntity<AppUser> getAppUserById(
      @Parameter(description = "ID of the App User to retrieve", example = "10000")
      @PathVariable Long id
  ) {
    AppUser appUser = appUserService.getAppUserById(id);
    if (appUser == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(appUser);
  }

  /**
   * Retrieves App Users filtered by type (optional).
   *
   * @param type Type filter for App Users (USER or ADMIN)
   * @return ResponseEntity containing the list of filtered App Users
   */
  @GetMapping("/user")
  @Operation(summary = "Get App Users by type", description = """
      Retrieves App Users filtered by type (optional).
      
      Valid types: USER, ADMIN
      
      Curl examples:
      ```bash
      # Get users with type filter
      curl 'http://localhost:8080/api/v1/app-users/user?type-filter=USER'
      
      # Get all users (no filter)
      curl http://localhost:8080/api/v1/app-users/user
      ```
      """)
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "List of filtered App Users",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AppUser.class),
              examples = @ExampleObject(value = """
                  [
                      {
                          "id": 10000,
                          "email": "user@example.com",
                          "type": "USER"
                      }
                  ]
                  """)
          )
      )
  })
  public ResponseEntity<List<AppUser>> getAppUsersByType(
      @Parameter(description = "Filter users by type (USER or ADMIN)")
      @RequestParam(required = false, name = "type-filter") String type
  ) {
    return ResponseEntity.ok(appUserService.getAppUsersByType(type));
  }
}
