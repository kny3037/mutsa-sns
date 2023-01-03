package com.mutsasnskimnayeong.domain.dto.comment;

import com.mutsasnskimnayeong.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateResponse {

    private Integer id;
    private String comment;
    private String userName;
    private Integer postId;
    private LocalDateTime createAt;

    public static CommentCreateResponse response(Comment comment){
        return CommentCreateResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createAt(comment.getCreatedAt())
                .build();
    }
}
