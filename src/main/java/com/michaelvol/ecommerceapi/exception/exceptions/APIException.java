package com.michaelvol.ecommerceapi.exception.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Builder
@Data
public class APIException extends RuntimeException {
    String message;
    Throwable cause;
    HttpStatus status;
    ZonedDateTime timestamp;
}
