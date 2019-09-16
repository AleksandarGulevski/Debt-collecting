package com.haselt.debtcollecting.config;

import com.haselt.appointapp.web.exception.MemberAlreadyRegisteredException;
import com.haselt.appointapp.web.exception.ResourceNotFoundException;
import com.haselt.appointapp.web.exception.ResourceValidationException;
import com.haselt.appointapp.web.util.ErrorCode;
import com.haselt.appointapp.web.util.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(MethodArgumentNotValidException exception) {
        log.debug(exception.getMessage(), exception);
        return error(exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList()));
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(ConstraintViolationException exception) {
        log.debug(exception.getMessage(), exception);
        return error(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(IllegalArgumentException exception) {
        log.debug(exception.getMessage(), exception);
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(DataIntegrityViolationException exception) {
        log.debug(exception.getMessage(), exception);
        return error(exception.getMessage());
    }

    @SuppressWarnings(value = "unchecked")
    private ErrorDto error(Object objectMessages) {
        StringBuilder stringBuilder = new StringBuilder();
        if (objectMessages instanceof ArrayList) {
            List<String> messages = (ArrayList) objectMessages;
            for (String message : messages) {
                stringBuilder.append(message);
                stringBuilder.append("\n");
            }
        } else if (objectMessages instanceof String) {
            stringBuilder.append(objectMessages);
            stringBuilder.append("\n");
        }
        return new ErrorDto(ErrorCode.FIELD_VALIDATION, stringBuilder.toString());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handle(ResourceNotFoundException exception) {
        log.debug(exception.getMessage(), exception);
        return new ErrorDto(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(ResourceValidationException exception) {
        log.debug(exception.getMessage(), exception);
        return new ErrorDto(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(MemberAlreadyRegisteredException exception) {
        log.debug(exception.getMessage(), exception);
        return new ErrorDto(exception.getCode(), exception.getMessage());
    }

}
