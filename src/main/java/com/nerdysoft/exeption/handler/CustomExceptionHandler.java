package com.nerdysoft.exeption.handler;

import com.nerdysoft.exeption.exeptions.ActivatedException;
import com.nerdysoft.exeption.exeptions.InvalidEmailException;
import com.nerdysoft.exeption.exeptions.NotFoundException;
import com.nerdysoft.exeption.exeptions.TokenException;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorAttributes errorAttributes;

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity notFoundException(WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(ActivatedException.class)
    public final ResponseEntity invalidUserRegistrationDataException(WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public final ResponseEntity invalidEmailException(WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(TokenException.class)
    public final ResponseEntity tokenException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return new HashMap<>(errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions
                .of(ErrorAttributeOptions.Include.MESSAGE)));
    }
}
