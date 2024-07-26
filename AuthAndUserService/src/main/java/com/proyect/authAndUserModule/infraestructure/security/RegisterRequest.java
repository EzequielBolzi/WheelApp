package com.proyect.authAndUserModule.infraestructure.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private Long favoriteTeamId;
    @NotBlank(message = "User name is mandatory")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    private String email;
    private int age;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;
}