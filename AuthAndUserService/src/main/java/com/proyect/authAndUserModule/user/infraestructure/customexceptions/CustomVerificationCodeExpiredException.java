package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomVerificationCodeExpiredException extends RuntimeException {
    public CustomVerificationCodeExpiredException(String message) {
        super(message);
    }
}