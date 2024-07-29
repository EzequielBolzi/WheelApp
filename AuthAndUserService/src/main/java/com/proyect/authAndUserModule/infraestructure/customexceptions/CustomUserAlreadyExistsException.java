package com.proyect.authAndUserModule.infraestructure.customexceptions;

public class CustomUserAlreadyExistsException extends  RuntimeException {
    public CustomUserAlreadyExistsException(String message) {
        super(message);
    }
}