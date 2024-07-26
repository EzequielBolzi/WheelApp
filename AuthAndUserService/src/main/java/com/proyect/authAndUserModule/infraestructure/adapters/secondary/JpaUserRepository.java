package com.proyect.authAndUserModule.infraestructure.adapters.secondary;

import com.proyect.authAndUserModule.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserName(String userName);
}
