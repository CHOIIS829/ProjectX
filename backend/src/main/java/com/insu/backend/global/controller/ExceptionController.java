package com.insu.backend.global.controller;

import com.insu.backend.global.exception.BaseException;
import com.insu.backend.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(BaseException e) {
        log.error(">>>>> [ERROR] {}", e.getMessage());
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(e.getStatusCode()))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentValidExceptionHandler(MethodArgumentNotValidException e) {

        List<String> errors = e.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList();

        log.error(">>>>> [ERROR] {}", errors.get(0));
        ErrorResponse body = ErrorResponse.builder()
                .code("400")
                .message(errors.get(0))
                .build();

        return ResponseEntity.badRequest().body(body);
    }
}
