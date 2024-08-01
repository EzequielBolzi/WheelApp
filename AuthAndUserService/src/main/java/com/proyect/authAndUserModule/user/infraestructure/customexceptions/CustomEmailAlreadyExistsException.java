package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomEmailAlreadyExistsException extends  RuntimeException {
    public CustomEmailAlreadyExistsException(String message) {
        super(message);
    }
}