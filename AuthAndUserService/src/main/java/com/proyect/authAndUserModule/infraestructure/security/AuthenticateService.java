package com.proyect.authAndUserModule.infraestructure.security;

import com.proyect.authAndUserModule.domain.model.Role;
import com.proyect.authAndUserModule.infraestructure.adapters.secondary.EmailAdapter;
import com.proyect.authAndUserModule.domain.dtos.VerifyUserDto;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomEmailAlreadyExistsException;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomInvalidEmailException;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomPasswordException;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomUserAlreadyExistsException;
import com.proyect.authAndUserModule.infraestructure.entities.UserEntity;
import com.proyect.authAndUserModule.infraestructure.adapters.secondary.JpaUserRepository;
import com.proyect.authAndUserModule.infraestructure.config.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final JpaUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailAdapter emailAdapter;

    public AuthenticationResponse register(RegisterRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new CustomPasswordException("Passwords must match.");
        }

        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            throw new CustomInvalidEmailException("Invalid email address.");
        }
        var user = UserEntity.builder()
                .favoriteTeamId(request.getFavoriteTeamId())
                .userName(request.getUserName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .registerDate(LocalDateTime.now())
                .role(Role.USER)
                .verificationCode(generateVerificationCode())
                .verificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15))
                .verified(false)
                .build();


            if (repository.findByEmail(user.getEmail()).isPresent()) {
                throw new CustomEmailAlreadyExistsException("Email is already in use.");
            }
            Optional<UserEntity> existUserName = repository.findByUserName(user.getUserName());
            if (existUserName.isPresent()) {
                throw new CustomUserAlreadyExistsException("User name is already in use.");
            }

            sendVerificationEmail(user);
            repository.save(user);

            var jwtToken = jwtService.generateToken(convertToUserDetails(user));
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

    }

    public void verifyUser(VerifyUserDto input) {
        Optional<UserEntity> optionalUser = repository.findByEmail(input.getEmail());
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            if(user.isVerified())
                throw new RuntimeException("Account is already verified.");
            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setVerified(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                repository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
    public void resendVerificationCode(String email) {
        Optional<UserEntity> optionalUser = repository.findByEmail(email);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            if (user.isVerified() && (user.getVerificationCodeExpiresAt() == null) ) {
                throw new RuntimeException("Account is already verified or verification code hasn't expired yet.");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            repository.save(user);


        } else {
            throw new RuntimeException("User not found");
        }
    }
    public AuthenticationResponse authenticateOAuth2(OAuth2User oAuth2User) {
        UserEntity user = findOrCreateUser(oAuth2User);
        var jwtToken = jwtService.generateToken(convertToUserDetails(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private UserEntity findOrCreateUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        return repository.findByEmail(email)
                .map(existingUser -> updateExistingUser(existingUser, oAuth2User))
                .orElseGet(() -> createNewUser(oAuth2User));
    }

    private UserEntity updateExistingUser(UserEntity existingUser, OAuth2User oAuth2User) {
        existingUser.setUserName(oAuth2User.getAttribute("name"));
        // Update other fields as necessary
        return repository.save(existingUser);
    }


    private UserEntity createNewUser(OAuth2User oAuth2User) {
        UserEntity newUser = UserEntity.builder()
                .email(oAuth2User.getAttribute("email"))
                .userName(oAuth2User.getAttribute("name"))
                .registerDate(LocalDateTime.now())
                .role(Role.USER)
                .verified(true)
                .build();

        return repository.save(newUser);
    }




    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<UserEntity> userOptional = repository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (!user.isVerified()) {
                throw new RuntimeException("Please verify your account before logging in.");
            }
        } else {
            throw new RuntimeException("User not found");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(convertToUserDetails(userOptional.orElseThrow()));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void sendVerificationEmail(UserEntity user) { //TODO: Update with company logo
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to Match!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code (Expires in 15 minutes):</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailAdapter.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    private UserDetails convertToUserDetails(UserEntity user) {
        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }

}
