package com.contentFormula.content.apicontent.infraestructure.customexceptions;

public class CustomVehicleAlreadyException extends RuntimeException {
    public CustomVehicleAlreadyException (String message){
        super(message);
    }
}
