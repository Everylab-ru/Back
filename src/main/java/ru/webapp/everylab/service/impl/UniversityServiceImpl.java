package ru.webapp.everylab.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.aspect.annotation.RequiresUserId;
import ru.webapp.everylab.dto.university.CommonUniversityResponse;
import ru.webapp.everylab.dto.university.FacultyResponse;
import ru.webapp.everylab.dto.university.UniversityResponse;
import ru.webapp.everylab.entity.university.Faculty;
import ru.webapp.everylab.entity.university.University;
import ru.webapp.everylab.entity.university.UserToUniversity;
import ru.webapp.everylab.entity.user.User;
import ru.webapp.everylab.mapper.FacultyMapper;
import ru.webapp.everylab.mapper.UniversityMapper;
import ru.webapp.everylab.repository.FacultyRepository;
import ru.webapp.everylab.repository.UniversityRepository;
import ru.webapp.everylab.repository.UserToUniversityRepository;
import ru.webapp.everylab.service.UniversityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final UniversityMapper universityMapper;
    private final FacultyMapper facultyMapper;
    private final UserToUniversityRepository userToUniversityRepository;

    @Override
    public CommonUniversityResponse getAllUniversitiesAndFaculties() {
        return CommonUniversityResponse.builder()
                .universities(getUniversities())
                .faculties(getFaculties())
                .build();
    }

    @Override
    public List<UniversityResponse> getUniversities() {
        return universityRepository.findAll()
                .stream()
                .map(universityMapper::toUniversityResponse)
                .toList();
    }

    @Override
    public List<FacultyResponse> getFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(facultyMapper::toFacultyResponse)
                .toList();
    }

    @Override
    public List<FacultyResponse> getFacultiesByUniversity(Integer universityId) {
        return facultyRepository.findByUniversityId(universityId)
                .stream()
                .map(facultyMapper::toFacultyResponse)
                .toList();
    }

    @RequiresUserId
    @Transactional
    @Override
    public void saveFacultyAndUniversity(Integer facultyId, Integer universityId, String userId) {
        UUID userUUID = UUID.fromString(userId);

        Optional<UserToUniversity> existingRecord = userToUniversityRepository
                .findByUserId(userUUID);
        existingRecord.ifPresent(userToUniversityRepository::delete);

        UserToUniversity userToUniversity = UserToUniversity.builder()
                .user(User.builder().id(userUUID).build())
                .university(University.builder().id(universityId).build())
                .faculty(Faculty.builder().id(facultyId).build())
                .build();

        userToUniversityRepository.save(userToUniversity);
    }
}
