package org.example.userservicedemo.controllerAdvice;

import org.example.userservicedemo.dto.ExceptionDto;
import org.example.userservicedemo.exceptions.IncorrectPasswordException;
import org.example.userservicedemo.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(userNotFoundException.getMessage());

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ExceptionDto> handleIncorrectPasswordException(IncorrectPasswordException incorrectPasswordException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(incorrectPasswordException.getMessage());

        return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
    }
}
