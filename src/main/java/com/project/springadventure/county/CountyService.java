package com.project.springadventure.county;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountyService {

  private static final String API_URL = "https://api.kartverket.no/kommuneinfo/v1/fylker/";
  private final RestTemplate restTemplate = new RestTemplate();

  public String getCounty(String countyNumber) {
    try {
      County county = restTemplate.getForObject(API_URL + countyNumber, County.class);
      return county != null ? county.getFylkesnavn() : "County not found";
    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch county information", e);
    }
  }

}
