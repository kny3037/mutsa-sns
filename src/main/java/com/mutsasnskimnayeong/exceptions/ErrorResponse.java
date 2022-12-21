package com.mutsasnskimnayeong.exceptions;

import com.sun.jdi.InternalException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;


}
