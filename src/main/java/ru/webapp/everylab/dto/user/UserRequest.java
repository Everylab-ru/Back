package ru.webapp.everylab.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        @Email
        String email
) { }
