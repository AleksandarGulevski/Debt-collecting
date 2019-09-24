package com.haselt.debtcollecting.exception;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(Integer code, String message) {
        super(code, message);
    }
}
