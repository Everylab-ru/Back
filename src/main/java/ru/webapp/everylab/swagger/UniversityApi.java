package ru.webapp.everylab.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestParam;
import ru.webapp.everylab.dto.university.CommonUniversityResponse;

@Tag(name = "University controller", description = "University API")
public interface UniversityApi {

    @Operation(summary = "Get all universities and faculties", description = "Returns a list of all existing universities and faculties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the lists",
                    content = @Content(schema = @Schema(implementation = CommonUniversityResponse.class)))
    })
    CommonUniversityResponse getUniversities();

    @Operation(summary = "Add new relation between User and University")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relation added successfully")
    })
    void saveFacultyAndUniversity(
            @RequestParam @NonNull @Positive Integer universityId,
            @RequestParam @NonNull @Positive Integer facultyId
    );
}
