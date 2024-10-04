package ru.webapp.everylab.dto.university;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Faculty Response Dto")
public record FacultyResponse(
        @Schema(description = "Faculty id", example = "1")
        Integer id,

        @Schema(description = "Faculty name", example = "ФИТР")
        String name,

        @Schema(description = "University id", example = "2")
        Integer universityId
) {
}
