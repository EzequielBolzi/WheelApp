package com.contentFormula.content.driverinfo.infraestructure.adapters.primary;

import com.contentFormula.content.driverinfo.infraestructure.customexceptions.CustomUpdatedDriverSuccesfullyException;
import com.contentFormula.content.driverinfo.infraestructure.customexceptions.CustomVehicleAlreadyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomVehicleAlreadyException.class)
    public ResponseEntity<String> handleVehicleAlreadyException(CustomVehicleAlreadyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(CustomUpdatedDriverSuccesfullyException.class)
    public ResponseEntity<String> handleUpdatedDriverSuccesfullyException(CustomUpdatedDriverSuccesfullyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
