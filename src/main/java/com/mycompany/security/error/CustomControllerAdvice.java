package com.mycompany.security.error;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

//https://auth0.com/blog/get-started-with-custom-error-handling-in-spring-boot-java/

@ControllerAdvice
class CustomControllerAdvice {
    @ExceptionHandler(NullPointerException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleNullPointerExceptions(
        Exception e
    ) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.NOT_FOUND; // 404

        return new ResponseEntity<>(
            new ErrorResponse(
              status, 
              e.getMessage()
            ),
            status
        );
    }

    // fallback method
    @ExceptionHandler(ResponseStatusException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleExceptions(
    		ResponseStatusException e
    ) {
        // ... potential custom logic

        HttpStatus status = e.getStatus() ; // HttpStatus.INTERNAL_SERVER_ERROR; // 500

    // converting the stack trace to String
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);
    String stackTrace = "";//stringWriter.toString();

        return new ResponseEntity<>(
            new ErrorResponse(
              status, 
              e.getReason(), //  getMessage(), 
              stackTrace // specifying the stack trace in case of 500s
            ),
            status
        );
    }
}