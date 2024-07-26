package com.proyect.authAndUserModule.infraestructure.customExceptions;

public class CustomPasswordException extends RuntimeException{
    public CustomPasswordException(String message){
        super(message);
    }

}
