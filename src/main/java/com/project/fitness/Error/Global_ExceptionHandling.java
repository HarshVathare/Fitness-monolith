package com.project.fitness.Error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class Global_ExceptionHandling {

    // 1 => User Not Found Exception
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Apierror> UserNotfoundException(UsernameNotFoundException ex) {
        Apierror apierror =  new Apierror(LocalDateTime.now(),"User not found by user_id "+ex.getMessage(),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apierror, HttpStatus.NOT_FOUND);
    }

    // 2 Authentication Exception
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Apierror> AuthenticationException(AuthenticationException ex) {
        Apierror apierror = new Apierror(LocalDateTime.now(), "Authentication Failed ..! "+ex.getMessage(), HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(apierror, HttpStatus.UNAUTHORIZED);
    }

    // 3 Jwt Exception
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Apierror> JwtException(JwtException ex) {
        Apierror apierror = new Apierror(LocalDateTime.now(), "Invalid JWT Token ..! "+ex.getMessage(), HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(apierror, HttpStatus.UNAUTHORIZED);
    }

    // 4 Access Denied ( Not Permission ) Exception
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Apierror> AccessDeniedException(AccessDeniedException ex) {
        Apierror apierror = new Apierror(LocalDateTime.now(), "Access denied : UnSufficient Permissions ..! "+ex.getMessage(), HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(apierror, HttpStatus.FORBIDDEN);
    }

    // 5 Exception only ( Internal Server Error )
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Apierror> Exception(Exception ex) {
        Apierror apierror = new Apierror(LocalDateTime.now(), "An Unaccepted error occurred ..! "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(apierror, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 6 Get message from validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Apierror> handleValidationException(MethodArgumentNotValidException ex) {

        // Get first validation error message
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        Apierror apierror = new Apierror(
                LocalDateTime.now(),
                message,
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(apierror, HttpStatus.BAD_REQUEST);
    }
}
