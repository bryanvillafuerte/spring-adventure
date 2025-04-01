package com.project.springadventure.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Long id) {
    super("App user not found with id: " + id);
  }

}
