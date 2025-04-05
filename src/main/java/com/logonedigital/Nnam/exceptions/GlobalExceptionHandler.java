package com.logonedigital.Nnam.exceptions;



import com.logonedigital.Nnam.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(Exception ex){
        return ResponseEntity
                .status(404)
                .body(new ErrorMessage(
                        404,
                        new Date(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.getReasonPhrase()
                ));
    }

    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ErrorMessage> handleResourceExistException(Exception ex){
        return ResponseEntity
                .status(400)
                .body(new ErrorMessage(
                        400,
                        new Date(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase()
                ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
