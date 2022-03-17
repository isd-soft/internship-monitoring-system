package org.inthergroup.ims.repository;


import org.inthergroup.ims.model.Role;
import org.inthergroup.ims.model.URole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(URole name);
}
