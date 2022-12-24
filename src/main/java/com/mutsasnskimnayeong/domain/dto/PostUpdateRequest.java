package com.mutsasnskimnayeong.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String body;
}
