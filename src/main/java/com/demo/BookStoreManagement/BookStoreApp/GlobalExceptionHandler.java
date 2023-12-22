package com.demo.BookStoreManagement.BookStoreApp;

import com.demo.BookStoreManagement.BookStoreApp.Exception.InvalidUserNamePasswordException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.LoginFailedException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.NoBookFound;
import com.demo.BookStoreManagement.BookStoreApp.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoBookFound.class)
    public ResponseEntity<Object> exception (NoBookFound exception)
    {
        return new  ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception (UserNotFoundException exception)
    {
        return new  ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ResponseEntity<Object> exception (LoginFailedException exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = InvalidUserNamePasswordException.class)
    public ResponseEntity<Object> exception (InvalidUserNamePasswordException exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
