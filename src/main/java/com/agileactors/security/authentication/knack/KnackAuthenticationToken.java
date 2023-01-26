package com.agileactors.security.authentication.knack;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class KnackAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  private final transient String knackAuthorizationToken;
  private final transient String userId;

  public KnackAuthenticationToken(Object principal,
                                  Object credentials,
                                  List<GrantedAuthority> authorities,
                                  String knackAuthorizationToken,
                                  String userId) {
    super(principal, credentials, authorities);
    this.knackAuthorizationToken = knackAuthorizationToken;
    this.userId = userId;
  }

  public String getKnackAuthorizationToken() {
    return knackAuthorizationToken;
  }
}
