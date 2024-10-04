package ru.webapp.everylab.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Schema(description = "Error response for validation Dto")
public record AppErrorCustom(

        @Schema(description = "HTTP status code", example = "400")
        int status,

        @Schema(description = "Timestamp of the error", example = "2024-06-28T08:26:53.025Z")
        LocalDateTime timestamp,

        Map<String, String> errors

) {}
