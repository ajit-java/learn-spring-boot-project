package de.scout24.financing.repository;

import java.util.List;

import de.scout24.financing.domain.ForumUser;
import org.springframework.data.repository.CrudRepository;

import de.scout24.financing.domain.Role;
import de.scout24.financing.domain.enums.ExfoRole;

public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findByName(ExfoRole role);
    Role getByName(ExfoRole role);
}
