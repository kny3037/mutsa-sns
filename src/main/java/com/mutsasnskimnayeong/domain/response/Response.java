package com.mutsasnskimnayeong.domain.response;

import com.mutsasnskimnayeong.exceptions.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Response <T>{
    private String resultCode;
    private T result;

    public static <T> Response<T> error(T result) {
        return new Response<>("ERROR", result);
    }

    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);
    }
}
