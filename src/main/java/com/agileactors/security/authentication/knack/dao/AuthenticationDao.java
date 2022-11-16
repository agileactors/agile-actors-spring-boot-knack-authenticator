package com.agileactors.security.authentication.knack.dao;

import com.agileactors.security.authentication.knack.dto.knack.AuthenticationResponseDto;
import com.agileactors.security.authentication.knack.properties.AuthenticationProperties;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationDao {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final AuthenticationProperties authenticationProperties;
  private final RestTemplate knackRestTemplate;

  public AuthenticationResponseDto authenticate(String username, String password) {
    Map<String, String> credentials = Map.of("email", username, "password", password);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(credentials);

    try {
      ResponseEntity<AuthenticationResponseDto> response =
          knackRestTemplate.postForEntity(authenticationProperties.getUrl(), request,
              AuthenticationResponseDto.class);

      return response.getBody();
    } catch (Exception e) {
      logger.error("Auth failed.", e);
      throw new BadCredentialsException("Something was wrong");
    }
  }
}
