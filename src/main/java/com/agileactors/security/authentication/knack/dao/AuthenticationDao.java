package com.agileactors.security.authentication.knack.dao;

import com.agileactors.security.authentication.knack.dto.AuthenticationResponseDto;
import com.agileactors.security.authentication.knack.properties.AuthenticationProperties;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationDao {


  private final AuthenticationProperties authenticationProperties;
  private final RestTemplate knackRestTemplate;

  public AuthenticationResponseDto authenticate(String username, String password) {
    log.info("Authenticating {}", username);
    Map<String, String> credentials = Map.of("email", username, "password", password);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(credentials);

    try {
      ResponseEntity<AuthenticationResponseDto> response =
          knackRestTemplate.postForEntity(authenticationProperties.getUrl(), request,
              AuthenticationResponseDto.class);
      log.info("Authenticating {} success", username);

      return response.getBody();
    } catch (Exception e) {
      log.error("Auth failed.", e);
      throw new BadCredentialsException("Something was wrong");
    }
  }
}
