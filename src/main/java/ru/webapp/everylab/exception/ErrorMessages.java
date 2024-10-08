package ru.webapp.everylab.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {
    public static final String USER_NOT_FOUND_MESSAGE = "User with id %s not found";
    public static final String USER_BY_EMAIL_NOT_FOUND_MESSAGE = "User with email %s not found";
    public static final String ROLE_NOT_FOUND_MESSAGE = "Role %s not found";

    public static final String DUPLICATE_RESOURCE_MESSAGE = "%s with this %s already exists";

    public static final String JWT_ERROR_MESSAGE = "Invalid JWT %s token";
    public static final String AUTH_HEADER_ERROR_MESSAGE = "Invalid authentication header";
}
