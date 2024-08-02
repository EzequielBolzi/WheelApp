package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomUserNotFoundException extends RuntimeException {
    public CustomUserNotFoundException(String message) {
        super(message);
    }
}