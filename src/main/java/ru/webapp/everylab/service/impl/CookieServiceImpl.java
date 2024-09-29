package ru.webapp.everylab.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.dto.authentication.AuthenticationResponse;
import ru.webapp.everylab.service.CookieService;
import ru.webapp.everylab.service.props.JwtProperties;

@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {

    private final JwtProperties jwtProperties;

    @Override
    public AuthenticationResponse createResponseWithTokens(String[] tokens, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setMaxAge((int) (jwtProperties.getRefresh() / 1000));
        cookie.setPath("/");

        // Устанавливаем SameSite через метод setAttribute
        cookie.setAttribute("SameSite", "Strict");

        response.addCookie(cookie);

        return AuthenticationResponse.builder()
                .accessToken(tokens[0])
                .build();
    }

    @Override
    public Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        return cookie;
    }
}
