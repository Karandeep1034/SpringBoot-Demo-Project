package com.example.newSpringBootProject.exceptionHandler;

import com.example.newSpringBootProject.exception.TaskException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ValidationException;
import java.net.SocketTimeoutException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(TaskException.class)
    public ResponseEntity<String> handleTaskException(TaskException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<String> handleTimeOutException(RuntimeException ex){
        return ResponseEntity.status(400).body("Something went wrong");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
