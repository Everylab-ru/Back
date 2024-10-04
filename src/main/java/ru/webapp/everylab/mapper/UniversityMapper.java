package ru.webapp.everylab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.webapp.everylab.dto.university.UniversityResponse;
import ru.webapp.everylab.entity.university.University;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UniversityMapper {

    UniversityResponse toUniversityResponse(University entity);
}
