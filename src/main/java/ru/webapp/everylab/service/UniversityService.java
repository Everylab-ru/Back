package ru.webapp.everylab.service;

import ru.webapp.everylab.dto.university.CommonUniversityResponse;
import ru.webapp.everylab.dto.university.FacultyResponse;
import ru.webapp.everylab.dto.university.UniversityResponse;

import java.util.List;

public interface UniversityService {
    CommonUniversityResponse getAllUniversitiesAndFaculties();

    List<UniversityResponse> getUniversities();

    List<FacultyResponse> getFaculties();

    List<FacultyResponse> getFacultiesByUniversity(Integer universityId);

    void saveFacultyAndUniversity(Integer facultyId, Integer universityId, String userId);
}
