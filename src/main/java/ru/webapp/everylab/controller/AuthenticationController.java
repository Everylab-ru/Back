package ru.webapp.everylab.controller;

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
import ru.webapp.everylab.service.CookieService;
import ru.webapp.everylab.swagger.AuthenticationApi;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;
    private final CookieService cookieService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public AuthenticationResponse register(
            @RequestBody @Valid UserRequest request,
            HttpServletResponse response
    ) {
        return cookieService.createResponseWithTokens(
                authenticationService.register(request),
                response
        );
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AuthenticationResponse authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) {
        return cookieService.createResponseWithTokens(
                authenticationService.authenticate(authenticationRequest),
                response
        );
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AuthenticationResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return cookieService.createResponseWithTokens(
                authenticationService.refreshToken(request, response),
                response
        );
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.logout(request, response);
    }
}
