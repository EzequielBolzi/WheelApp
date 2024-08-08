package com.contentFormula.content.f1driverinfo.infraestructure.customexceptions;

public class CustomVehicleAlreadyException extends RuntimeException {
    public CustomVehicleAlreadyException (String message){
        super(message);
    }
}
