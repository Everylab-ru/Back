package ru.webapp.everylab.service;

import ru.webapp.everylab.entity.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
    User getUserById(UUID id);
    List<User> getAllUsers();
}
