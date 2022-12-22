package com.mutsasnskimnayeong.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserLoginRequest {
    private String userName;
    private String password;
}
