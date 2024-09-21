package ru.webapp.everylab.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User request Dto")
public record UserRequest(
        @NotBlank
        @Schema(description = "Username", example = "John Doe")
        String username,

        @NotBlank
        @Schema(description = "Password", example = "password")
        String password,

        @NotBlank
        @Email
        @Schema(description = "Email", example = "john.doe@example.com")
        String email
) {
}
