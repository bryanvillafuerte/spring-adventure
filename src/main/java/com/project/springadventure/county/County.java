package com.project.springadventure.county;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class County {
  private String fylkesnavn;

  public County(String fylkesnavn) {
    this.fylkesnavn = fylkesnavn;
  }

  public County() {
  }

  public String getFylkesnavn() {
    return fylkesnavn;
  }

  public void setFylkesnavn(String fylkesnavn) {
    this.fylkesnavn = fylkesnavn;
  }

  @Override
  public String toString() {
    return "County{" +
        "fylkesnavn='" + fylkesnavn + '\'' +
        '}';
  }
}