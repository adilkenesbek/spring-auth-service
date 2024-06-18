package kz.pet.eon.handler.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
