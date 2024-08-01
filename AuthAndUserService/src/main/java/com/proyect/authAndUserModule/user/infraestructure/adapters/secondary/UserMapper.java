package com.proyect.authAndUserModule.user.infraestructure.adapters.secondary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.authAndUserModule.user.domain.dtos.UserDto;
import com.proyect.authAndUserModule.user.domain.model.User;
import com.proyect.authAndUserModule.user.infraestructure.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static UserEntity fromDomainModel(User user) {
        return new UserEntity(
                user.getId(),
                user.getFavoriteTeamId(),
                user.getUserName(),
                user.getEmail(),
                user.getAge(),
                user.getPassword(),
                user.getConfirmPassword(),
                user.getRegisterDate(),
                user.getRole(),
                user.getProfilePicture(),
                user.getVerificationCode(),
                user.getVerificationCodeExpiresAt(),
                user.isEnabled()
        );
    }

    public static User toDomainModel(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFavoriteTeamId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getAge(),
                userEntity.getPassword(),
                userEntity.getConfirmPassword(),
                userEntity.getRegisterDate(),
                userEntity.getRole(),
                userEntity.getProfilePicture(),
                userEntity.getVerificationCode(),
                userEntity.getVerificationCodeExpiresAt(),
                userEntity.isVerified()
        );
    }

    public static UserDto toDto(User domain) {
        if (domain == null) return null;

        return new UserDto(
                domain.getFavoriteTeamId(),
                domain.getUserName(),
                domain.getEmail(),
                domain.getAge(),
                domain.getProfilePicture(),
                domain.getRegisterDate()
        );

    }
    public static List<UserDto> toDto(List<User> domains) {
        return domains.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}