package com.hastype.api.infra;

import com.hastype.api.exceptions.*;
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

    @ExceptionHandler(SessionDoesntExistException.class)
    private ResponseEntity<String> sessionDoesntExistsHandler(SessionDoesntExistException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(SessionIsExpiredException.class)
    private ResponseEntity<String> sessionIsExpiredHandler(SessionIsExpiredException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(UserIsAlreadyLoggedException.class)
    private ResponseEntity<String> userIsAlreadyLoggedHandler(UserIsAlreadyLoggedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
