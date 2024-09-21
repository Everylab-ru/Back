package ru.webapp.everylab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.entity.user.User;
import ru.webapp.everylab.repository.UserRepository;
import ru.webapp.everylab.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User updateUser(UUID id, User user) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {
    }

    @Override
    public User getUserById(UUID id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
