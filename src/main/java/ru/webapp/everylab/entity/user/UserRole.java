package ru.webapp.everylab.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.webapp.everylab.entity.Role;

@Entity
@Table(name = "userRoles")
@Getter
@Setter
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "usersId")
    private User user;

    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "usersId")
    private Role role;
}
