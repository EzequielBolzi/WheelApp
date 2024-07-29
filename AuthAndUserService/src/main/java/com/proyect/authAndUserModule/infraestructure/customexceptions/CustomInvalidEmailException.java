package com.proyect.authAndUserModule.infraestructure.customexceptions;

public class CustomInvalidEmailException extends RuntimeException {
    public CustomInvalidEmailException(String message) {
        super(message);
    }
}
