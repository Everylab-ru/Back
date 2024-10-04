package ru.webapp.everylab.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Error response Dto")
public record AppError(
        @Schema(description = "HTTP status code", example = "500")
        int status,

        @Schema(description = "Error message", example = "Internal server error")
        String message,

        @Schema(description = "Timestamp of the error", example = "2024-06-28T08:26:53.025Z")
        LocalDateTime timestamp
) {
}
