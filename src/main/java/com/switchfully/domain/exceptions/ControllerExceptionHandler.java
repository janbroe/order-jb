package com.switchfully.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(WrongCredentialsException.class)
    protected void wrongCredentialsException(WrongCredentialsException exception, HttpServletResponse response) throws IOException {
        log.info("WrongCredentialsException -> " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected void unauthorizedException(UnauthorizedException exception, HttpServletResponse response) throws IOException {
        log.info("UnauthorizedException -> " + exception.getMessage());
        response.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }
}
