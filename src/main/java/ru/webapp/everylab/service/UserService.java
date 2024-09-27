package ru.webapp.everylab.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.dto.user.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse updateUser(UUID id, UserRequest user);

    void deleteUser(UUID id);

    UserResponse getUserById(HttpServletRequest request);
}
