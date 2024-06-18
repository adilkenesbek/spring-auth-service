package kz.pet.eon.handler.domain;

public class ErrorCode {

    public static final String ERROR_USER_ALREADY_EXISTS = "User with this username already exists";
    public static final String ERROR_VALIDATION_FAILED = "Validation Failed";
    public static final String ERROR_BAD_CREDENTIALS = "Invalid username or password";
    public static final String ERROR_TOKEN_EXPIRED = "JWT Token expired";
    public static final String ERROR_INVALID_TOKEN = "JWT Token invalid";
}
