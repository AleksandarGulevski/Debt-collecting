package com.haselt.debtcollecting.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

    private final Integer code;
    private final String message;
}
