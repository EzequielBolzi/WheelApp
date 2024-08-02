package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomInvalidVerificationCodeException extends RuntimeException {
    public CustomInvalidVerificationCodeException(String message) {
        super(message);
    }
}