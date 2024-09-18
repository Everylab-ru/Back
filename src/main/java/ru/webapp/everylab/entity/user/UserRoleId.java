package ru.webapp.everylab.entity.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@RequiredArgsConstructor
@Getter
@Setter
public class UserRoleId implements Serializable {

    private UUID usersId;
    private Long rolesId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return usersId.equals(that.usersId) && rolesId.equals(that.rolesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, rolesId);
    }
}
