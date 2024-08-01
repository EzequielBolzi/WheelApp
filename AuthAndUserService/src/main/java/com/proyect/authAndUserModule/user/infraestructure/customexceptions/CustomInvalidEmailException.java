package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomInvalidEmailException extends RuntimeException {
    public CustomInvalidEmailException(String message) {
        super(message);
    }
}
