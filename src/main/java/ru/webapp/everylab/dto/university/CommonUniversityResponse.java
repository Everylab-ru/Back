package ru.webapp.everylab.dto.university;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "Common University Response Dto")
public record CommonUniversityResponse(
        @Schema(description = "Universities array")
        List<UniversityResponse> universities,

        @Schema(description = "Faculties array")
        List<FacultyResponse> faculties
) {
}
