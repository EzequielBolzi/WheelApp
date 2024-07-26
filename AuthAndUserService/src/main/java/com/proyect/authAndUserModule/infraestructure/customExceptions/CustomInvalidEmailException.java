package com.proyect.authAndUserModule.infraestructure.customExceptions;

public class CustomInvalidEmailException extends RuntimeException {
    public CustomInvalidEmailException(String message) {
        super(message);
    }
}
