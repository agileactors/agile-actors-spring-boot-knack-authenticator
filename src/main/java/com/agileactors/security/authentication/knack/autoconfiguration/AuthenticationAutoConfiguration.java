package com.agileactors.security.authentication.knack.autoconfiguration;

import com.agileactors.security.authentication.knack.KnackAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthenticationAutoConfiguration {

  @Autowired
  private KnackAuthenticationProvider knackAuthenticationProvider;

  @Bean
  public SecurityFilterChain authenticationFilterChain(HttpSecurity http) throws Exception {
    http.authenticationProvider(knackAuthenticationProvider);
    return http.build();
  }
}
