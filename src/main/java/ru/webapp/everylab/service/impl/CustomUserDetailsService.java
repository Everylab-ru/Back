package ru.webapp.everylab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.entity.user.SecurityUser;
import ru.webapp.everylab.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return new SecurityUser(
                userRepository.findById(UUID.fromString(uuid))
                        .orElseThrow()
        );
    }
}
