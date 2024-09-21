package ru.webapp.everylab.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.dto.authentication.AuthenticationResponse;

import ru.webapp.everylab.service.RegistrationService;
import ru.webapp.everylab.service.props.JwtProperties;
import ru.webapp.everylab.swagger.RegistrationApi;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegistrationController implements RegistrationApi {

    private final RegistrationService registrationService;
    private final JwtProperties jwtProperties;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public AuthenticationResponse register(
            @RequestBody @Valid UserRequest request,
            HttpServletResponse response
    ) {
        String[] tokens = registrationService.register(request);

        Cookie cookie = createTokenCookie(tokens[1]);
        response.addCookie(cookie);

        return AuthenticationResponse.builder()
                .accessToken(tokens[0])
                .build();
    }

    private Cookie createTokenCookie( String value) {
        Cookie cookie = new Cookie("token", value);

        cookie.setMaxAge((int) (jwtProperties.getRefresh() / 1000));
        cookie.setPath("/");

        return cookie;
    }
}
