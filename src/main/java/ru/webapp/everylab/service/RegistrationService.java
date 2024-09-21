package ru.webapp.everylab.service;

import ru.webapp.everylab.entity.user.User;

public interface RegistrationService {
    String[] register(User user);
}
