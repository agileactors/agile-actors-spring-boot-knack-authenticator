package com.agileactors.security.authentication.knack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class User {
  private String id;
  private String token;
  @JsonProperty("profile_keys")
  private List<String> profileKeys;

  public void setId(String id) {
    this.id = id;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setProfileKeys(List<String> profileKeys) {
    this.profileKeys = profileKeys;
  }

  public String getId() {
    return id;
  }

  public String getToken() {
    return token;
  }

  public List<String> getProfileKeys() {
    return profileKeys;
  }
}
