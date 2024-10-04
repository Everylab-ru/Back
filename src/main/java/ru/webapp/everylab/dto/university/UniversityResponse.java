package ru.webapp.everylab.dto.university;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "University Response Dto")
public record UniversityResponse(
        @Schema(description = "University id", example = "1")
        Integer id,

        @Schema(description = "University name", example = "BNTU")
        String name
) {
}
