package ru.webapp.everylab.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Authentication Request Dto")
public record AuthenticationRequest(

        @NotBlank
        @Email
        @Schema(description = "Email", example = "john.doe@example.com")
        String email,

        @NotBlank
        @Schema(description = "Password", example = "password1")
        String password
) {
}
