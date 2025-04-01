package com.project.springadventure.appUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private AppUserService appUserService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createAppUser_ValidInput_ReturnsCreatedUser() throws Exception {
    AppUserDTO dto = new AppUserDTO();
    dto.setEmail("test@example.com");
    dto.setType("USER");

    AppUser appUser = new AppUser("test@example.com", "USER");
    appUser.setId(10000L);

    when(appUserService.createAppUser(any(AppUser.class))).thenReturn(appUser);

    mockMvc.perform(post("/api/v1/app-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(10000))
        .andExpect(jsonPath("$.email").value("test@example.com"))
        .andExpect(jsonPath("$.type").value("USER"));
  }

  @Test
  void createAppUser_InvalidInput_ReturnsBadRequest() throws Exception {
    AppUserDTO dto = new AppUserDTO();
    dto.setEmail("invalid-email");
    dto.setType("INVALID");

    mockMvc.perform(post("/api/v1/app-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getAllAppUsers_ReturnsPagedUsers() throws Exception {
    List<AppUser> users = List.of(
        new AppUser("user1@example.com", "USER"),
        new AppUser("user2@example.com", "ADMIN")
    );
    when(appUserService.getAllAppUsers(any(PageRequest.class)))
        .thenReturn(new PageImpl<>(users));

    mockMvc.perform(get("/api/v1/app-users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.length()").value(2))
        .andExpect(jsonPath("$.content[0].email").value("user1@example.com"));
  }

  @Test
  void getAppUserById_ExistingId_ReturnsUser() throws Exception {
    AppUser appUser = new AppUser("test@example.com", "USER");
    appUser.setId(10000L);

    when(appUserService.getAppUserById(10000L)).thenReturn(appUser);

    mockMvc.perform(get("/api/v1/app-users/10000"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(10000))
        .andExpect(jsonPath("$.email").value("test@example.com"));
  }

  @Test
  void getAppUsersByType_ValidType_ReturnsFilteredUsers() throws Exception {
    List<AppUser> users = List.of(
        new AppUser("user1@example.com", "USER"),
        new AppUser("user2@example.com", "USER")
    );
    when(appUserService.getAppUsersByType("USER")).thenReturn(users);

    mockMvc.perform(get("/api/v1/app-users/user")
            .param("type-filter", "USER"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].type").value("USER"));
  }

}
