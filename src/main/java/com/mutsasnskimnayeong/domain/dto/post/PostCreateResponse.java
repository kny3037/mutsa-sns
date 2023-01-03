package com.mutsasnskimnayeong.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateResponse {
    private String message;
    private Integer postId;
}
