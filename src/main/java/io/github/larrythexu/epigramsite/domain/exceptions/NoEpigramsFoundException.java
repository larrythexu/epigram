package io.github.larrythexu.epigramsite.domain.exceptions;

public class NoEpigramsFoundException extends RuntimeException {
  public NoEpigramsFoundException(String message) {
    super(message);
  }
}
