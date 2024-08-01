package com.proyect.authAndUserModule.user.infraestructure.customexceptions;

public class CustomPasswordException extends RuntimeException{
    public CustomPasswordException(String message){
        super(message);
    }

}
