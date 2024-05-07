package com.emailservice.exceptions;

import com.emailservice.controller.EmailController;
import com.emailservice.model.StatusResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;

@RestControllerAdvice(assignableTypes = EmailController.class)
public class EmailRestControllerAdvice {

    @ExceptionHandler(AuthenticationFailedException.class)
    public StatusResponse AuthenticationFailedExceptionHandler(AuthenticationFailedException afe){
        return new StatusResponse(202, "Wrong userName and password");
    }

    @ExceptionHandler(SendFailedException.class)
    public StatusResponse SendFailedExceptionHandler(SendFailedException sfe){
        return new StatusResponse(204, "Incorrect Recipient mail");
    }

    @ExceptionHandler(Exception.class)
    public StatusResponse genericExceptionHandler(Exception e){
        return new StatusResponse(404, "Something went wrong! try after some time");
    }
}
