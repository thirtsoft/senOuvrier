package com.ouvriers.repository;

import com.ouvriers.models.Addresse;
import com.ouvriers.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
