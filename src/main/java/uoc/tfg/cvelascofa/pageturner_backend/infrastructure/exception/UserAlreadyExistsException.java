package uoc.tfg.cvelascofa.pageturner_backend.infrastructure.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
