package com.mutsasnskimnayeong.domain.dto;

import com.mutsasnskimnayeong.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateRequest {
    private String title;
    private String body;

    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .body(this.body)
                .build();
    }
}
