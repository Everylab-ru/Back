package ru.webapp.everylab.entity.user;

import jakarta.persistence.*;
import lombok.*;
import ru.webapp.everylab.entity.role.Role;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @MapsId("rolesId")
    @JoinColumn(name = "roles_id")
    private Role role;
}
