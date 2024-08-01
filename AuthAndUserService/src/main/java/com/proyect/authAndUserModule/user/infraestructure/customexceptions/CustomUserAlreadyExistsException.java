package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomUserAlreadyExistsException extends  RuntimeException {
    public CustomUserAlreadyExistsException(String message) {
        super(message);
    }
}