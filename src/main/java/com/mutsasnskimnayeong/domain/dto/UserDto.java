package com.mutsasnskimnayeong.domain.dto;

import com.mutsasnskimnayeong.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String userName;
    private String password;
    private UserRole role;
/*
    public UserJoinResponse toResponse() {
        return new UserJoinResponse(this.id, this.userName);
    }*/


}
