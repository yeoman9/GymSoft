package com.gymsoft.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymsoft.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName( String roleName );
}
