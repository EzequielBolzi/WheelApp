package com.proyect.authAndUserModule.infraestructure.config;

import com.proyect.authAndUserModule.application.service.UserService;
import com.proyect.authAndUserModule.application.usecases.UserUseCaseImpl;
import com.proyect.authAndUserModule.domain.port.out.UserRepositoryPort;
import com.proyect.authAndUserModule.infraestructure.adapters.secondary.JpaUserRepository;
import com.proyect.authAndUserModule.infraestructure.adapters.secondary.JpaUserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ServiceAppConfig {
    private final JpaUserRepository repository;

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort ){
        return new UserService(
                new UserUseCaseImpl(userRepositoryPort)
        );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter){
        return jpaUserRepositoryAdapter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
