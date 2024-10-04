package ru.webapp.everylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webapp.everylab.entity.university.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
