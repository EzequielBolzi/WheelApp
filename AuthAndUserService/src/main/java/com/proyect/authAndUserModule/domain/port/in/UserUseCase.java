package com.proyect.authAndUserModule.domain.port.in;

import com.proyect.authAndUserModule.domain.model.User;
import java.util.List;

import java.util.Optional;

public interface UserUseCase {
    User createUser(User user);
    boolean deleteUser(Long userId);
    Optional<User> updateUser(Long userId, User user);
    Optional<User> getUser(Long userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
    List<User> getAllUsers();


}
