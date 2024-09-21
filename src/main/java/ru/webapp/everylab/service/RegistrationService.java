package ru.webapp.everylab.service;

import ru.webapp.everylab.dto.user.UserRequest;

public interface RegistrationService {
    String[] register(UserRequest request);
}
