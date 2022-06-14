package com.kykapple.springbootplayground.exception;

import com.kykapple.springbootplayground.exception.dto.ExceptionHandleResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT = "exception class = {}, status = {}, message = {}";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionHandleResponse> handleRuntimeException(RuntimeException e) {
        log.error(LOG_FORMAT, e.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        ExceptionHandleResponse exceptionHandleResponse = new ExceptionHandleResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionHandleResponse);
    }

}
