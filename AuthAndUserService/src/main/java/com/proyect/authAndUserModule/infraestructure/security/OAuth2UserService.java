package com.proyect.authAndUserModule.infraestructure.security;

import com.proyect.authAndUserModule.domain.model.Role;
import com.proyect.authAndUserModule.infraestructure.entities.UserEntity;
import com.proyect.authAndUserModule.infraestructure.adapters.secondary.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final JpaUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        UserEntity user = findOrCreateUser(oAuth2User);

        return new CustomOAuth2User(oAuth2User, user);
    }

    public UserEntity findOrCreateUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        return userRepository.findByEmail(email)
                .map(existingUser -> updateExistingUser(existingUser, oAuth2User))
                .orElseGet(() -> createNewUser(oAuth2User));
    }

    private UserEntity updateExistingUser(UserEntity existingUser, OAuth2User oAuth2User) {
        existingUser.setUserName(oAuth2User.getAttribute("name"));
        // Update other fields as necessary
        return userRepository.save(existingUser);
    }

    private UserEntity createNewUser(OAuth2User oAuth2User) {
        String name = oAuth2User.getAttribute("name");
        UserEntity newUser = UserEntity.builder()
                .email(oAuth2User.getAttribute("email"))
                .userName(name.replace(" ",""))
                .registerDate(LocalDateTime.now())
                .role(Role.USER)
                .verified(true)
                .build();
        return userRepository.save(newUser);
    }
}