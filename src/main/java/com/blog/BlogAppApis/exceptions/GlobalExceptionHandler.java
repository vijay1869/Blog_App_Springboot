package com.blog.BlogAppApis.exceptions;

import com.blog.BlogAppApis.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice//this is exception handler,has methods ,
//we need to say which method to execute when an specific exception comes
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//when we get this exception,this method will be executed
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        //put
       ex.getBindingResult().getAllErrors().forEach((error)->{
           String fieldName=((FieldError) error).getField();
           String message=error.getDefaultMessage();
           errors.put(fieldName,message);
       });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
