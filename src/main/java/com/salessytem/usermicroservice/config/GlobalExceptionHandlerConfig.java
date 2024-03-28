package com.salessytem.usermicroservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandlerConfig extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = { NullPointerException.class, IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request) {
        log.error("Null Pointer Exception or Invalid Argument Exception", ex);
        return handleExceptionInternal(ex, "Null pointer or invalid argument", null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        log.error("Data integrity violation in the database", ex);
        return handleExceptionInternal(ex, "Data integrity violation in the database", null, HttpStatus.CONFLICT, request);
    }


    record ExceptionResponse(String message, String code, String description){};

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex, WebRequest request) {

        String message = String.format("Error in invocation of %s, Error message of %s",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString(),
                ex.getMessage()
                );
        log.error( message );

        return new ResponseEntity<>(
                new ExceptionResponse( message, "EX001", "Exception generated " )
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {

        String message = String.format("Error in invocation of %s, Error message of %s",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString(),
                ex.getMessage()
        );
        log.error( message );

        return new ResponseEntity<>(
                new ExceptionResponse( message, "EX001", "Runtime Exception generated " )
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleArrayIndexOutOfBoundsException(
            ArrayIndexOutOfBoundsException ex, WebRequest request) {


        log.error("Array Index Out of Bounds", ex);

        String errorMessage = "Error: Array Index Out of Bounds. " + ex.getMessage();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = String.format("Error in validation of %s fields",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString()
        );
        log.error(message, ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
