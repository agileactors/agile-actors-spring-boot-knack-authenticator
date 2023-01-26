package com.agileactors.security.authentication.knack.dto;

public class Session {
  private User user;

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
