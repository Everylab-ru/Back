package ru.webapp.everylab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.dto.user.UserResponse;
import ru.webapp.everylab.entity.user.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(UserRequest request);
}
