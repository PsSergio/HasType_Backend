package com.hastype.api.infra;

import com.hastype.api.exceptions.EmailAlreadyExistsException;
import com.hastype.api.exceptions.LoginFailedException;
import com.hastype.api.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(LoginFailedException.class)
    private ResponseEntity<String> loginFailedHandler(LoginFailedException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<String> emailAlreadyExistsHandler(EmailAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
