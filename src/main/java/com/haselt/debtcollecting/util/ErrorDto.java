package com.haselt.debtcollecting.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ErrorDto implements Serializable {

    private int code;
    private String message;
    private SeverityType severityType;

    public ErrorDto(int code, String message){
        this.code = code;
        this.message = message;
        this.severityType = SeverityType.ERROR;

    }

}
