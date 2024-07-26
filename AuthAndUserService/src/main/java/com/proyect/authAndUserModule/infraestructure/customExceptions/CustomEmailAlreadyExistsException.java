package com.proyect.authAndUserModule.infraestructure.customExceptions;

public class CustomEmailAlreadyExistsException extends  RuntimeException {
    public CustomEmailAlreadyExistsException(String message) {
        super(message);
    }
}