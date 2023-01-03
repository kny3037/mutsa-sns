package com.mutsasnskimnayeong.domain.entity;

import com.mutsasnskimnayeong.domain.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String comment;

    public CommentDto dto(){
        return CommentDto.builder()
                .id(this.id)
                .comment(this.comment)
                .post(this.post)
                .user(this.user)
                .createAt(this.getCreatedAt())
                .build();
    }

}
