package com.kimlngo.userserv.advice;

import com.kimlngo.userserv.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RecordNotFound customerNotFoundHandler(CustomerNotFoundException e) {
        return new RecordNotFound(e.getMessage());
    }
}
