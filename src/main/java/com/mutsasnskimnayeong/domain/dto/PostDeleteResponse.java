package com.mutsasnskimnayeong.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDeleteResponse {
    private String message;
    private Integer postId;

}