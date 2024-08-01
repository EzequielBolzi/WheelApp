package com.proyect.authAndUserModule.user.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long id;
    private Long favoriteTeamId;
    private String userName;
    private String email;
    private int age;
    private String password;
    private String confirmPassword;
    private LocalDateTime registerDate;
    private Role role;
    private String profilePicture;
    private String verificationCode;
    private LocalDateTime verificationCodeExpiresAt;
    private boolean enabled;

}
