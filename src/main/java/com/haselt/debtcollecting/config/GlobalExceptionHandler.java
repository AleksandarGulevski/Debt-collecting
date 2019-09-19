package com.haselt.debtcollecting.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@ControllerAdvice
@Component
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handle(MethodArgumentNotValidException exception) {
//        log.debug(exception.getMessage(), exception);
//        return error(exception.getBindingResult().getFieldErrors()
//                .stream()
//                .map(FieldError::getDefaultMessage)
//                .collect(Collectors.toList()));
//    }
//
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handle(ConstraintViolationException exception) {
//        log.debug(exception.getMessage(), exception);
//        return error(exception.getConstraintViolations()
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList()));
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handle(IllegalArgumentException exception) {
//        log.debug(exception.getMessage(), exception);
//        return error(exception.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handle(DataIntegrityViolationException exception) {
//        log.debug(exception.getMessage(), exception);
//        return error(exception.getMessage());
//    }
//
//    @SuppressWarnings(value = "unchecked")
//    private ErrorDto error(Object objectMessages) {
//        StringBuilder stringBuilder = new StringBuilder();
//        if (objectMessages instanceof ArrayList) {
//            List<String> messages = (ArrayList) objectMessages;
//            for (String message : messages) {
//                stringBuilder.append(message);
//                stringBuilder.append("\n");
//            }
//        } else if (objectMessages instanceof String) {
//            stringBuilder.append(objectMessages);
//            stringBuilder.append("\n");
//        }
//        return new ErrorDto(ErrorCode.FIELD_VALIDATION, stringBuilder.toString());
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDto handle(ResourceNotFoundException exception) {
//        log.debug(exception.getMessage(), exception);
//        return new ErrorDto(exception.getCode(), exception.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handle(ResourceValidationException exception) {
//        log.debug(exception.getMessage(), exception);
//        return new ErrorDto(exception.getCode(), exception.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handle(MemberAlreadyRegisteredException exception) {
//        log.debug(exception.getMessage(), exception);
//        return new ErrorDto(exception.getCode(), exception.getMessage());
//    }

}
