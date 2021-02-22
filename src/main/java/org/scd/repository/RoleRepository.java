package org.scd.repository;

import org.scd.model.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findAllById(Long i);
    Iterable<Role> findAll();
}
