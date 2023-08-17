package com.michaelvol.ecommerceapi.exception;

import com.michaelvol.ecommerceapi.exception.exceptions.APIException;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.exception.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleAPIRequestException(BadRequestException exception) {
        APIException apiException = APIException.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .cause(exception)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleAPIRequestException(NotFoundException exception) {
        APIException apiException = APIException.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .cause(exception)
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


}
