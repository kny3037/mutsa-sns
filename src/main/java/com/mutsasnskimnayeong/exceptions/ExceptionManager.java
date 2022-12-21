package com.mutsasnskimnayeong.exceptions;

import com.mutsasnskimnayeong.domain.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> AppExceptionHandler(AppException e){
        Response response = Response.builder()
                .resultCode(e.getErrorCode().name())
                .result(ErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .message(e.getMessage())
                        .build()
                ).build();
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(response);
    }


}
