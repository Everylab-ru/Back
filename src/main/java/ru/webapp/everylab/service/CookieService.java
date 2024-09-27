package ru.webapp.everylab.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import ru.webapp.everylab.dto.authentication.AuthenticationResponse;

public interface CookieService {
    AuthenticationResponse createResponseWithTokens(String[] tokens, HttpServletResponse response);

    Cookie deleteCookie(String name);
}
