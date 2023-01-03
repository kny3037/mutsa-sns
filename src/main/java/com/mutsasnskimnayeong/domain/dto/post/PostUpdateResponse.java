package com.mutsasnskimnayeong.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateResponse {
    private String message;
    private Integer postId;
}
