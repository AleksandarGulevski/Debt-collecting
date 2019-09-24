package com.haselt.debtcollecting.exception;

public class DebtorAlreadyExistsException extends BaseException {

    public DebtorAlreadyExistsException(Integer code, String message) {
        super(code, message);
    }
}
