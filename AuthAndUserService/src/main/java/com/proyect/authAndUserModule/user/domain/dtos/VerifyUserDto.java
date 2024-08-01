package com.proyect.authAndUserModule.user.domain.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUserDto {
    private String email;
    private String verificationCode;
}