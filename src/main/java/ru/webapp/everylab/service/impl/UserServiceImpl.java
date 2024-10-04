package ru.webapp.everylab.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.dto.user.UserResponse;
import ru.webapp.everylab.entity.user.User;
import ru.webapp.everylab.exception.ResourceNotFoundException;
import ru.webapp.everylab.mapper.UserMapper;
import ru.webapp.everylab.repository.UserRepository;
import ru.webapp.everylab.service.JwtService;
import ru.webapp.everylab.service.UserService;

import java.util.UUID;

import static ru.webapp.everylab.exception.ErrorMessages.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public UserResponse updateUser(UUID id, UserRequest user) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public UserResponse getUserById(HttpServletRequest request) {
        String token = jwtService.extractTokenFromRequest(request);
        String userId = jwtService.extractUserIdFromToken(token);
        UUID id = UUID.fromString(userId);

        User user = findUserByIdOrThrow(id);
        return userMapper.toUserResponse(user);
    }

    private User findUserByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
    }
}
