package com.contentFormula.content.driverinfo.infraestructure.customexceptions;

public class CustomVehicleAlreadyException extends RuntimeException {
    public CustomVehicleAlreadyException (String message){
        super(message);
    }
}
