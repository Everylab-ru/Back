package ru.webapp.everylab.service.impl;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.dto.authentication.AuthenticationRequest;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.entity.role.Role;
import ru.webapp.everylab.entity.user.SecurityUser;
import ru.webapp.everylab.entity.user.User;
import ru.webapp.everylab.entity.user.UserRole;
import ru.webapp.everylab.entity.user.UserRoleId;
import ru.webapp.everylab.exception.ResourceNotFoundException;
import ru.webapp.everylab.exception.UnauthorizedException;
import ru.webapp.everylab.repository.RoleRepository;
import ru.webapp.everylab.repository.UserRepository;
import ru.webapp.everylab.repository.UserRoleRepository;
import ru.webapp.everylab.service.AuthenticationService;
import ru.webapp.everylab.service.CookieService;
import ru.webapp.everylab.service.JwtService;

import java.io.IOException;
import java.util.UUID;

import static ru.webapp.everylab.entity.role.RoleName.USER;
import static ru.webapp.everylab.exception.ErrorMessages.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final CookieService cookieService;

    public String[] register(UserRequest request) {

        UserDetails userDetails = buildUser(request);
        User savedUser = userRepository.save(((SecurityUser) userDetails).getUser());
        Role defaultRole = findRoleByNameOrThrow();

        UserRole userRole = buildUserRole(savedUser, defaultRole);
        userRoleRepository.save(userRole);

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new String[]{accessToken, refreshToken};
    }

    @Override
    public String[] authenticate(AuthenticationRequest request) {
        User user = findUserByEmail(request.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId(), request.password())
        );

        UserDetails userDetails = new SecurityUser(user);
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new String[] {accessToken, refreshToken};
    }

    @Override
    public String[] refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String refreshToken = getTokenFromCookie(request);
        final String userID;

        if(refreshToken == null) {
            return null;
        }

        String newAccessToken = null;
        String newRefreshToken = null;

        try {
            userID = jwtService.extractUserIdFromToken(refreshToken);

            if(userID != null) {
                User user = findUserById(UUID.fromString(userID));
                UserDetails userDetails = new SecurityUser(user);

                if(jwtService.isTokenValid(refreshToken, userDetails)) {
                    newAccessToken = jwtService.generateToken(userDetails);
                    newRefreshToken = jwtService.generateRefreshToken(userDetails);
                }
            }
        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, JWT_ERROR_MESSAGE);
            return null;
        }

        return new String[] {newAccessToken, newRefreshToken};
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            String userID = jwtService.extractUserIdFromToken(token);
            User user = findUserById(UUID.fromString(userID));
            UserDetails userDetails = new SecurityUser(user);

            if(jwtService.isTokenValid(token, userDetails)) {
                Cookie cookie = cookieService.deleteCookie("token");
                response.addCookie(cookie);
            } else {
                throw new UnauthorizedException(JWT_ERROR_MESSAGE);
            }
        } else {
            throw new UnauthorizedException(AUTH_HEADER_ERROR_MESSAGE);
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_BY_EMAIL_NOT_FOUND_MESSAGE, email)));
    }

    private User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));
    }

    private Role findRoleByNameOrThrow() {
        return roleRepository.findByName(USER)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ROLE_NOT_FOUND_MESSAGE, USER)));
    }


    private UserDetails buildUser(UserRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .build();

        return SecurityUser.builder()
                .user(user)
                .build();
    }

    private UserRole buildUserRole(User savedUser, Role defaultRole) {
        UserRoleId combinedPK = UserRoleId.builder()
                .usersId(savedUser.getId())
                .rolesId(defaultRole.getId())
                .build();

        return UserRole.builder()
                .id(combinedPK)
                .user(savedUser)
                .role(defaultRole)
                .build();
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
