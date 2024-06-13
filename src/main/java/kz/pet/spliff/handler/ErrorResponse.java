package kz.pet.spliff.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String message;
}
