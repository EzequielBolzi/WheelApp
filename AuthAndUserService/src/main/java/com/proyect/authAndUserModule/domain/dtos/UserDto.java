package com.proyect.authAndUserModule.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long favoriteTeamId;
    private String userName;
    private String email;
    private int age;
    private String profilePicture;
    private LocalDateTime registerDate;
}