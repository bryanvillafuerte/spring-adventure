package com.project.springadventure.county;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountyController.class)
class CountyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CountyService countyService;

  @Test
  void getCountyName_ValidNumber_ReturnsCountyName() throws Exception {
    when(countyService.getCounty("03")).thenReturn("Oslo");

    mockMvc.perform(get("/county/03"))
        .andExpect(status().isOk())
        .andExpect(content().string("Oslo"));
  }

  @Test
  void getCountyName_InvalidNumber_ReturnsNotFound() throws Exception {
    when(countyService.getCounty(anyString()))
        .thenThrow(new RuntimeException("Failed to fetch county information"));

    mockMvc.perform(get("/county/99"))
        .andExpect(status().isInternalServerError());
  }
}