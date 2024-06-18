package kz.pet.eon.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @Size(min = 5, max = 50, message = "Invalid username: Must be of 5 - 50 characters.")
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 5, message = "Invalid password: Must contain at least 5 characters")
    private String password;
}
