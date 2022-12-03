package com.agileactors.security.authentication.knack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class User {
  private String id;
  private String token;
  @JsonProperty("profile_keys")
  private List<String> profileKeys;
}
