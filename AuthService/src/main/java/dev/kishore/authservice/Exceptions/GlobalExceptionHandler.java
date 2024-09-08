package dev.kishore.authservice.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)  // Set the desired HTTP status code
    public ResponseEntity<String> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
