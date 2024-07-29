package com.proyect.authAndUserModule.infraestructure.customexceptions;

public class CustomEmailAlreadyExistsException extends  RuntimeException {
    public CustomEmailAlreadyExistsException(String message) {
        super(message);
    }
}