package com.proyect.authAndUserModule.user.infraestructure.adapters.primary;

import com.proyect.authAndUserModule.user.infraestructure.customexceptions.*;
import org.springframework.http.HttpStatus;
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
    @ExceptionHandler(CustomVerifyAccountBeforeLogginException.class)
    public ResponseEntity<String> handleVerifyAccountBeforeLogginException(CustomVerifyAccountBeforeLogginException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
    @ExceptionHandler(CustomAccountAlreadyVerifiedException.class)
    public ResponseEntity<String> handleAccountAlreadyVerifiedException(CustomAccountAlreadyVerifiedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CustomVerificationCodeExpiredException.class)
    public ResponseEntity<String> handleVerificationCodeExpiredException(CustomVerificationCodeExpiredException ex) {
        return ResponseEntity.status(HttpStatus.GONE).body(ex.getMessage());
    }

    @ExceptionHandler(CustomInvalidVerificationCodeException.class)
    public ResponseEntity<String> handleInvalidVerificationCodeException(CustomInvalidVerificationCodeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CustomUserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(CustomUserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
