package com.proyect.authAndUserModule.infraestructure.adapters.primary;

import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomEmailAlreadyExistsException;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomInvalidEmailException;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomPasswordException;
import com.proyect.authAndUserModule.infraestructure.customexceptions.CustomUserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomEmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(CustomEmailAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(CustomUserAlreadyExistsException.class)
    public ResponseEntity<String> handleCustomUserAlreadyExistsException(CustomUserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(CustomPasswordException.class)
    public ResponseEntity<String> handleCustomPasswordException(CustomPasswordException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(CustomInvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(CustomInvalidEmailException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
