package ru.webapp.everylab.controller;

import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.webapp.everylab.dto.university.CommonUniversityResponse;
import ru.webapp.everylab.service.UniversityService;
import ru.webapp.everylab.swagger.UniversityApi;


@RestController
@RequestMapping("api/v1/university")
@RequiredArgsConstructor
public class UniversityController implements UniversityApi {

    private final UniversityService universityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Override
    public CommonUniversityResponse getUniversities() {
        return universityService.getAllUniversitiesAndFaculties();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void saveFacultyAndUniversity(
            @RequestParam @NonNull @Positive Integer universityId,
            @RequestParam @NonNull @Positive Integer facultyId) {
        universityService.saveFacultyAndUniversity(facultyId, universityId, "userId");
    }
}
