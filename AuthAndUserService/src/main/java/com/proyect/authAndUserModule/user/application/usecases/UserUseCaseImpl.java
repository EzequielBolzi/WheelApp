package com.proyect.authAndUserModule.user.application.usecases;

import com.proyect.authAndUserModule.user.domain.model.User;
import com.proyect.authAndUserModule.user.domain.port.in.UserUseCase;
import com.proyect.authAndUserModule.user.domain.port.out.UserRepositoryPort;
import lombok.AllArgsConstructor;
import java.util.List;

import java.util.Optional;


@AllArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return userRepositoryPort.updateUser(id,user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepositoryPort.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepositoryPort.findByUserName(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }


}
