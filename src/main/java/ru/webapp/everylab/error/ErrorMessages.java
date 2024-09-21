package ru.webapp.everylab.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {
    public static final String USER_NOT_FOUND_MESSAGE = "User with id %s not found";
    public static final String ROLE_NOT_FOUND_MESSAGE = "Role %s not found";
}
