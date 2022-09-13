package com.agileactors.chapteree_app.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = InvalidIdException.class)
    protected ResponseEntity<Object> invalidIdHandler(Exception e){
        return new ResponseEntity<>("Invalid ID", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidLevelException.class)
    protected ResponseEntity<Object> invalidLevelHandler(Exception e){
        return new ResponseEntity<>("Invalid Level", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NoCoachException.class)
    protected ResponseEntity<Object> noCoachHandler(Exception e){
        return new ResponseEntity<>("Chapteree has no coach", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataAccessException.class)
    protected ResponseEntity<Object> invalidPostBodyException(Runtime ex){
        return new ResponseEntity<>("All fields required", HttpStatus.BAD_REQUEST);
    }
}
