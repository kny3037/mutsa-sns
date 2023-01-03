package com.mutsasnskimnayeong.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDeleteResponse {
    private String message;
    private Integer postId;

}
