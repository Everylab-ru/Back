package ru.webapp.everylab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.webapp.everylab.entity.role.Role;
import ru.webapp.everylab.entity.role.RoleName;
import ru.webapp.everylab.entity.user.SecurityUser;
import ru.webapp.everylab.entity.user.User;
import ru.webapp.everylab.entity.user.UserRole;
import ru.webapp.everylab.entity.user.UserRoleId;
import ru.webapp.everylab.repository.RoleRepository;
import ru.webapp.everylab.repository.UserRepository;
import ru.webapp.everylab.repository.UserRoleRepository;
import ru.webapp.everylab.service.JwtService;
import ru.webapp.everylab.service.RegistrationService;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String[] register(User user) {

        UserDetails userDetails = buildUser(user);

        User savedUser = userRepository.save(((SecurityUser) userDetails).getUser());

        Role defaultRole = roleRepository.findByName(RoleName.USER).orElse(null);   //todo выбросить исключение вместо null
        UserRoleId combinedPK = new UserRoleId(savedUser.getId(), defaultRole.getId());
        UserRole userRole = UserRole.builder()
                .id(combinedPK)
                .user(savedUser)
                .role(defaultRole)
                .build();

        userRoleRepository.save(userRole);

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new String[]{accessToken, refreshToken};
    }


    private UserDetails buildUser(User request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        return SecurityUser.builder()
                .user(user)
                .build();
    }
}
