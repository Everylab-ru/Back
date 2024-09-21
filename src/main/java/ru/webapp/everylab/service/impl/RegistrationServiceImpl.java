package ru.webapp.everylab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.dto.user.UserRequest;
import ru.webapp.everylab.entity.role.Role;
import ru.webapp.everylab.entity.user.SecurityUser;
import ru.webapp.everylab.entity.user.User;
import ru.webapp.everylab.entity.user.UserRole;
import ru.webapp.everylab.entity.user.UserRoleId;
import ru.webapp.everylab.exception.ResourceNotFoundException;
import ru.webapp.everylab.repository.RoleRepository;
import ru.webapp.everylab.repository.UserRepository;
import ru.webapp.everylab.repository.UserRoleRepository;
import ru.webapp.everylab.service.JwtService;
import ru.webapp.everylab.service.RegistrationService;

import static ru.webapp.everylab.entity.role.RoleName.USER;
import static ru.webapp.everylab.error.ErrorMessages.ROLE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
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
}
