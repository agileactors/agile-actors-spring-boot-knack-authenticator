package com.agileactors.security.authentication.knack.properties;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.auth.knack")
public class AuthenticationProperties {
  private String applicationId;
  private String restApiKey;
  private URI url;
  private List<String> whitelistedProfiles;
  private Duration connectTimeout;
  private Duration readTimeout;

  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  public String getRestApiKey() {
    return restApiKey;
  }

  public void setRestApiKey(String restApiKey) {
    this.restApiKey = restApiKey;
  }

  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  public List<String> getWhitelistedProfiles() {
    return whitelistedProfiles;
  }

  public void setWhitelistedProfiles(List<String> whitelistedProfiles) {
    this.whitelistedProfiles = whitelistedProfiles;
  }

  public Duration getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(Duration connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public Duration getReadTimeout() {
    return readTimeout;
  }

  public void setReadTimeout(Duration readTimeout) {
    this.readTimeout = readTimeout;
  }
}
