package com.agileactors.security.authentication.knack.autoconfiguration;

import com.agileactors.security.authentication.knack.KnackAuthenticationProvider;
import com.agileactors.security.authentication.knack.dao.AuthenticationDao;
import com.agileactors.security.authentication.knack.properties.AuthenticationProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@EnableConfigurationProperties({AuthenticationProperties.class})
public class AuthenticationAutoConfiguration {

  @Bean
  public AuthenticationProvider knackAuthenticationProvider(
      AuthenticationProperties authenticationProperties,
      RestTemplate knackRestTemplate) {

    return new KnackAuthenticationProvider(
        new AuthenticationDao(authenticationProperties, knackRestTemplate),
        authenticationProperties);
  }

  @Bean
  public RestTemplate knackRestTemplate(AuthenticationProperties authenticationProperties) {
    return new RestTemplateBuilder()
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("X-Knack-Application-Id", authenticationProperties.getApplicationId())
        .defaultHeader("X-Knack-REST-API-Key", authenticationProperties.getRestApiKey())
        .setReadTimeout(authenticationProperties.getReadTimeout())
        .setConnectTimeout(authenticationProperties.getConnectTimeout())
        .build();
  }
}
