package com.agileactors.security.authentication.knack;

import com.agileactors.security.authentication.knack.dao.AuthenticationDao;
import com.agileactors.security.authentication.knack.dto.knack.User;
import com.agileactors.security.authentication.knack.properties.AuthenticationProperties;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public class KnackAuthenticationProvider implements AuthenticationProvider {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AuthenticationDao authenticationDao;
  private final AuthenticationProperties authenticationProperties;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    var email = authentication.getName();

    var password = authentication.getCredentials().toString();

    var authenticationResponse = authenticationDao.authenticate(email, password);

    if (!isAccessAllowed(authenticationResponse.getSession().getUser())) {
      logger.error(email + " not allowed");
      throw new BadCredentialsException("Something was wrong");
    }

    return new UsernamePasswordAuthenticationToken(email, password,
        authenticationResponse.getSession().getUser().getProfileKeys().stream()
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
  }

  private boolean isAccessAllowed(User user) {
    if (authenticationProperties.getWhitelistedProfiles() == null
        || authenticationProperties.getWhitelistedProfiles().isEmpty()) {
      return true;
    }
    List<String> userProfiles = user.getProfileKeys();
    return userProfiles.stream()
        .anyMatch(userProfile ->
            authenticationProperties.getWhitelistedProfiles().contains(userProfile));

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
