package com.assignment.banking.exception;

import com.assignment.banking.exception.custom.AccountNotFound;
import com.assignment.banking.exception.custom.CustomException;
import com.assignment.banking.exception.custom.UserAlreadyPresent;
import com.assignment.banking.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyPresent.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorResponse orderNotFoundException(UserAlreadyPresent exception){
        return new ErrorResponse(HttpStatus.ALREADY_REPORTED, exception.getMessage());
    }

    @ExceptionHandler(AccountNotFound.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse accountNotFound(AccountNotFound exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse customException(CustomException exception){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
