package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomVerifyAccountBeforeLogginException extends RuntimeException {
    public CustomVerifyAccountBeforeLogginException(String message) {
        super(message);
    }
}

