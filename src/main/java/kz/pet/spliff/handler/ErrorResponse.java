package kz.pet.spliff.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String details;
    private Map<String, String> errors;

    public ErrorResponse(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(int statusCode, String message, String details, Map<String, String> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
        this.errors = errors;
    }


}
