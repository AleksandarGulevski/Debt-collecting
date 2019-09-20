package com.haselt.debtcollecting.exception;

public class ResourceValidationException extends BaseException {

    public ResourceValidationException(Integer code, String message) {
        super(code, message);
    }
}
