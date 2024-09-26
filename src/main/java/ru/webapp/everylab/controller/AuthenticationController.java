package ru.webapp.everylab.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.webapp.everylab.dto.authentication.AuthenticationRequest;
import ru.webapp.everylab.dto.authentication.AuthenticationResponse;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.service.AuthenticationService;
import ru.webapp.everylab.service.props.JwtProperties;
import ru.webapp.everylab.swagger.AuthenticationApi;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;
    private final JwtProperties jwtProperties;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public AuthenticationResponse register(
            @RequestBody @Valid UserRequest request,
            HttpServletResponse response
    ) {
        String[] tokens = authenticationService.register(request);
        Cookie cookie = createTokenCookie(tokens[1]);
        response.addCookie(cookie);

        return AuthenticationResponse.builder()
                .accessToken(tokens[0])
                .build();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AuthenticationResponse authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) {
        String[] tokens = authenticationService.authenticate(authenticationRequest);

        Cookie cookie = createTokenCookie(tokens[1]);
        response.addCookie(cookie);

        return AuthenticationResponse.builder()
                .accessToken(tokens[0])
                .build();
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AuthenticationResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String[] tokens = authenticationService.refreshToken(request, response);

        Cookie cookie = createTokenCookie(tokens[1]);
        response.addCookie(cookie);

        return AuthenticationResponse.builder()
                .accessToken(tokens[0])
                .build();
    }

    private Cookie createTokenCookie(String value) {
        Cookie cookie = new Cookie("token", value);

        cookie.setMaxAge((int) (jwtProperties.getRefresh() / 1000));
        cookie.setPath("/");

        return cookie;
    }
}
