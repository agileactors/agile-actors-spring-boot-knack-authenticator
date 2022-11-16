package com.agileactors.security.authentication.knack.properties;

import java.net.URI;
import java.net.URL;
import java.util.List;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.auth.knack")
@ConditionalOnProperty(prefix = "application.auth.knack", value = "url")
@Data
public class AuthenticationProperties {
  private URI url;
  private List<String> whitelistedProfiles;
}
