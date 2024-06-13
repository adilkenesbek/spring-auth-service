package kz.pet.spliff.handler.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
