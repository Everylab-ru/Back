package ru.webapp.everylab.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.webapp.everylab.dto.user.UserResponse;
import ru.webapp.everylab.service.UserService;
import ru.webapp.everylab.swagger.UserApi;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public UserResponse getUser(HttpServletRequest request) {
        return userService.getUserById(request);
    }
}
