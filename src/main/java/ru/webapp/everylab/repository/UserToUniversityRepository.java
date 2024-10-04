package ru.webapp.everylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.everylab.entity.university.UserToUniversity;

import java.util.Optional;
import java.util.UUID;

public interface UserToUniversityRepository extends JpaRepository<UserToUniversity, Integer> {
    Optional<UserToUniversity> findByUserId(UUID userId);
    //void deleteUserById(UUID userId);
}
