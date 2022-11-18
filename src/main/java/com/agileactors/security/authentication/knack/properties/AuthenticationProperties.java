package com.agileactors.security.authentication.knack.properties;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.auth.knack")
@ConditionalOnProperty(prefix = "application.auth.knack", value = "url")
@Getter
@Setter
public class AuthenticationProperties {
  private String applicationId;
  private String restApiKey;
  private URI url;
  private List<String> whitelistedProfiles;
  private Duration connectTimeout;
  private Duration readTimeout;
}
