package com.gymsoft.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymsoft.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{

    Optional<User> findByUsername( String username );

    Optional<User> findByEmail( String email );

    List<User> findAll();

}
