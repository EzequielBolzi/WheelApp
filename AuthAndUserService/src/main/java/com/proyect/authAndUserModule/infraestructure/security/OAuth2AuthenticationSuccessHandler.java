package com.proyect.authAndUserModule.infraestructure.security;

import com.proyect.authAndUserModule.infraestructure.config.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final OAuth2UserService oAuth2UserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(oauth2User, oAuth2UserService.findOrCreateUser(oauth2User));
        String token = jwtService.generateToken(customOAuth2User.getUser());

        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
    }
}