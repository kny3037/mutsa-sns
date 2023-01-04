package com.mutsasnskimnayeong.domain.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Integer id;
    private String comment;
    private User user;
    private Post post;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createAt;

    public CommentResponse response(){
        return CommentResponse.builder()
                .id(this.id)
                .comment(this.comment)
                .userName(user.getUserName())
                .postId(post.getId())
                .createAt(this.createAt)
                .build();
    }

}
