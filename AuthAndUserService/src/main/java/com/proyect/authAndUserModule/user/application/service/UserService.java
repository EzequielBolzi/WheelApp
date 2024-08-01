package com.proyect.authAndUserModule.user.application.service;

import com.proyect.authAndUserModule.user.domain.model.User;
import com.proyect.authAndUserModule.user.domain.port.in.UserUseCase;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class UserService implements UserUseCase {

     private final UserUseCase userUseCaseImpl;
    @Override
    public User createUser(User user) {
        return userUseCaseImpl.createUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userUseCaseImpl.deleteUser(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return userUseCaseImpl.updateUser(id,user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userUseCaseImpl.getUser(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userUseCaseImpl.getUserByEmail(email);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userUseCaseImpl.getUserByUserName(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return userUseCaseImpl.getAllUsers();
    }

    }
