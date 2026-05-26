package com.chance.litestock.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private final Integer code;


    public BaseException(String msg) {
        super(msg);
        this.code = 500;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }



    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }




}
