package com.proyect.authAndUserModule.infraestructure.adapters.secondary;

import com.proyect.authAndUserModule.domain.model.User;
import com.proyect.authAndUserModule.domain.port.out.UserRepositoryPort;
import com.proyect.authAndUserModule.infraestructure.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.fromDomainModel(user);
        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);
        return UserMapper.toDomainModel(savedUserEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(UserMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return jpaUserRepository.findByUserName(userName).map(UserMapper::toDomainModel);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(UserMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> updateUser(Long userId,User updateUser) {
        return jpaUserRepository.findById(userId).map(user -> {

            if (updateUser.getFavoriteTeamId() != null) {
                user.setFavoriteTeamId(updateUser.getFavoriteTeamId());
            }
            if (updateUser.getUserName() != null) {
                user.setUserName(updateUser.getUserName());
            }
            if (updateUser.getEmail() != null) {
                user.setEmail(updateUser.getEmail());
            }
            if (updateUser.getAge() != 0) {
                user.setAge(updateUser.getAge());
            }
            if (updateUser.getPassword() != null) {
                String encodedPassword = passwordEncoder.encode(updateUser.getPassword());
                user.setPassword(encodedPassword);
                user.setConfirmPassword(encodedPassword);
            }
            if (updateUser.getProfilePicture() != null) {
                user.setProfilePicture(updateUser.getProfilePicture());
            }

            return UserMapper.toDomainModel(jpaUserRepository.save(user));
        });
    }

    @Override
    public boolean deleteById(Long id) {
        if (jpaUserRepository.existsById(id)) {
            jpaUserRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
