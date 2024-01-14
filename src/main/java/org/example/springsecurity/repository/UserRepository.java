package org.example.springsecurity.repository;

import org.example.springsecurity.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByName(String username);
}
