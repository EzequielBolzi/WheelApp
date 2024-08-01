package com.proyect.authAndUserModule.user.domain.port.in;

import com.proyect.authAndUserModule.user.domain.model.User;
import java.util.List;

import java.util.Optional;

public interface UserUseCase {
    User createUser(User user);
    boolean deleteUser(Long userId);
    Optional<User> updateUser(Long userId, User user);
    Optional<User> getUser(Long userId);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUserName(String userName);
    List<User> getAllUsers();


}
