package com.proyect.authAndUserModule.infraestructure.entities;

import com.proyect.authAndUserModule.domain.model.Role;
import com.proyect.authAndUserModule.domain.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "favorite_team_id", nullable = true)
    private Long favoriteTeamId;


    @Column(name = "user_name", length = 255, unique = true)
    private String userName;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "password",length = 255)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "register_date", nullable = false, length = 255)
    private LocalDateTime registerDate;

    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "profile_picture", nullable = true, length = 255)
    private String profilePicture;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;

    @Column(name = "verified")
    private boolean verified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getUserName() {
        return userName;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isVerified() {
        return true;
    }

    public static UserEntity fromDomainModel(User user) {
        return new UserEntity(user.getId(), user.getFavoriteTeamId(), user.getUserName(), user.getEmail(), user.getAge(),
                user.getPassword(), user.getConfirmPassword(), user.getRegisterDate(), user.getRole(), user.getProfilePicture(), user.getVerificationCode(),user.getVerificationCodeExpiresAt(),user.isEnabled());
    }

    public User toDomainModel() {
        return new User(id, favoriteTeamId, userName, email, age, password,confirmPassword, registerDate, role, profilePicture,verificationCode,verificationCodeExpiresAt, verified);
    }
}