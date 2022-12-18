package com.agileactors.security.authentication.knack;

import com.agileactors.security.authentication.knack.dao.AuthenticationDao;
import com.agileactors.security.authentication.knack.dto.User;
import com.agileactors.security.authentication.knack.properties.AuthenticationProperties;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
@Slf4j
public class KnackAuthenticationProvider implements AuthenticationProvider {

  private final AuthenticationDao authenticationDao;
  private final AuthenticationProperties authenticationProperties;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    var email = authentication.getName();

    var password = authentication.getCredentials().toString();

    var authenticationResponse = authenticationDao.authenticate(email, password);

    if (!isAccessAllowed(authenticationResponse.getSession().getUser())) {
      log.error(email + " not allowed for profile keys "
          + authenticationResponse.getSession().getUser().getProfileKeys());
      throw new BadCredentialsException("Something was wrong");
    }

    log.info("User {} access granted with profile keys {}", email,
        authenticationResponse.getSession().getUser().getProfileKeys());

    var user = authenticationResponse.getSession()
        .getUser();

    return new KnackAuthenticationToken(email, password,
        authenticationResponse.getSession().getUser().getProfileKeys().stream()
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
        user.getToken(), user.getId());
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
