package com.proyect.authAndUserModule.infraestructure.customExceptions;

public class CustomUserAlreadyExistsException extends  RuntimeException {
    public CustomUserAlreadyExistsException(String message) {
        super(message);
    }
}