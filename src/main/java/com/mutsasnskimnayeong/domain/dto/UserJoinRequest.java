package com.mutsasnskimnayeong.domain.dto;

import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;

    public User toEntity(String password){
        return User.builder()
                .userName(this.userName)
                .password(password)
                .role(UserRole.USER)
                .build();
    }
}
