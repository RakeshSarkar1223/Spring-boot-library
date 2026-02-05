//package com.library.library.exception;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
////import java.util.Objects;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex){
//        Map<String , String> errors = new HashMap<>();
//        ex.getBindingResult()
//                .getFieldErrors()
//                .forEach(error -> {
//                    errors.put(error.getField(), error.getDefaultMessage());
//                });
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("timestamps" ,LocalDateTime.now());
//        response.put("status", HttpStatus.BAD_REQUEST.value());
//        response.put("errors", errors);
//        return response;
//    }
//
//    @ExceptionHandler(BookNotFoundException.class)
//    public Map<String, String> handleBookNotFound(BookNotFoundException ex){
//        Map<String, String> errors = new HashMap<>();
//        errors.put("timestamps", LocalDateTime.now().toString());
//        errors.put("status", HttpStatus.BAD_REQUEST.value()+"");
//        errors.put("error", ex.getMessage());
//        return errors;
//    }
//
////    @ExceptionHandler(Exception.class)
////    public Map<String , String> handleGeneralException(Exception ex){
////        Map<String, String> error = new HashMap<>();
////        error.put("timestamps", LocalDateTime.now().toString());
////        error.put("status", HttpStatus.BAD_REQUEST.value() + "");
////        error.put("error","Something went Wrong");
////        return error;
////    }
//}


package com.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Validation & custom exceptions already handled elsewhere

    // ✅ Handle unmapped URLs / internal errors safely
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 404);
        response.put("error", "Endpoint not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ✅ LAST RESORT (VERY SAFE)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 500);
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
