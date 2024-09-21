package ru.webapp.everylab.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.webapp.everylab.entity.user.User;

import ru.webapp.everylab.service.RegistrationService;
import ru.webapp.everylab.service.props.JwtProperties;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final JwtProperties jwtProperties;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String register(
            @RequestBody User user,
            HttpServletResponse response
    ) {
        String[] tokens = registrationService.register(user);
        Cookie cookie = new Cookie("token", tokens[1]);

        cookie.setMaxAge((int) (jwtProperties.getRefresh() / 1000));
        cookie.setPath("/");

        response.addCookie(cookie);
        return tokens[0];
    }
}
