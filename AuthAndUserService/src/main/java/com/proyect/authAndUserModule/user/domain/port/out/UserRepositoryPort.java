package com.proyect.authAndUserModule.user.domain.port.out;

import com.proyect.authAndUserModule.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
    List<User> findAll();
    Optional<User> updateUser(Long userId, User user);
    boolean deleteById(Long id);
}
