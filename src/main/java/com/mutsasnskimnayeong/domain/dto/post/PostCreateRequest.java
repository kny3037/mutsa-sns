package com.mutsasnskimnayeong.domain.dto.post;

import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateRequest {
    private String title;
    private String body;

    public Post toEntity(User user){
        return Post.builder()
                .user(user)
                .title(this.title)
                .body(this.body)
                .build();
    }
}
