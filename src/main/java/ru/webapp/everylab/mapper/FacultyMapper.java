package ru.webapp.everylab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.webapp.everylab.dto.university.FacultyResponse;
import ru.webapp.everylab.entity.university.Faculty;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FacultyMapper {

    @Mapping(source = "university.id", target = "universityId")
    FacultyResponse toFacultyResponse(Faculty faculty);
}
