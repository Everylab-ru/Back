package ru.webapp.everylab.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.webapp.everylab.dto.authentication.AuthenticationRequest;
import ru.webapp.everylab.dto.user.UserRequest;

import java.io.IOException;

public interface AuthenticationService {
    String[] register(UserRequest request);

    String[] authenticate(AuthenticationRequest request);

    String[] refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
