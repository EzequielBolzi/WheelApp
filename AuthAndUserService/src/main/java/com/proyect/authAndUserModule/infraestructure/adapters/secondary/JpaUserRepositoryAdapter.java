package com.proyect.authAndUserModule.infraestructure.adapters.secondary;

import com.proyect.authAndUserModule.domain.model.User;
import com.proyect.authAndUserModule.domain.port.out.UserRepositoryPort;
import com.proyect.authAndUserModule.infraestructure.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.fromDomainModel(user);
        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);
        return savedUserEntity.toDomainModel();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return jpaUserRepository.findByUserName(userName).map(UserEntity::toDomainModel);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(UserEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> updateUser(User user) {
        if (jpaUserRepository.existsById(user.getId())) {
            UserEntity userEntity = UserEntity.fromDomainModel(user);
            UserEntity updateUserEntity = jpaUserRepository.save(userEntity);
            return Optional.of(updateUserEntity.toDomainModel());
        }

        return Optional.empty();
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
