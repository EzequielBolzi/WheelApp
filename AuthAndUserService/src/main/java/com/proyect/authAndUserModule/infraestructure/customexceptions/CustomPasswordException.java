package com.proyect.authAndUserModule.infraestructure.customexceptions;

public class CustomPasswordException extends RuntimeException{
    public CustomPasswordException(String message){
        super(message);
    }

}
