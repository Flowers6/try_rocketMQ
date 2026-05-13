package com.chance.litestock.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class Result<T> {
    private Integer code = HttpStatus.OK.value();
    private String msg = HttpStatus.OK.getReasonPhrase();
    private T data;
    private String traceId;

    public static <T> ResponseEntity<Result<T>> ok() {
        return ResponseEntity.status(HttpStatus.OK.value()).body(new Result<>());
    }

    public static <T> ResponseEntity<Result<T>> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return ResponseEntity.status(HttpStatus.OK.value()).body(result);
    }

    public static <T> ResponseEntity<Result<T>> fail(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return ResponseEntity.status(code).body(result);
    }

    public static <T> ResponseEntity<Result<T>> fail(Integer code, String msg, String traceId) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setTraceId(traceId);
        return ResponseEntity.status(code).body(result);
    }

    public static <T> ResponseEntity<Result<T>> fail(String msg) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

}
