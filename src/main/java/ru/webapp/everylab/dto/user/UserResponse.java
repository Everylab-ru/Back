package ru.webapp.everylab.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "User Request Dto")
public record UserResponse(

        @Schema(description = "Username", example = "John Doe")
        String username,

        @Schema(description = "Email", example = "john.doe@example.com")
        String email
) {
}
