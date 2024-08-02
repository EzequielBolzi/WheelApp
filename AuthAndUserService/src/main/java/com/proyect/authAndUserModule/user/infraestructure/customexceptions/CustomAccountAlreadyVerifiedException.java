package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomAccountAlreadyVerifiedException extends RuntimeException {
    public CustomAccountAlreadyVerifiedException(String message) {
        super(message);
    }
}