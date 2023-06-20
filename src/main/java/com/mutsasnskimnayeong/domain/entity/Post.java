package com.mutsasnskimnayeong.domain.entity;

import com.mutsasnskimnayeong.domain.dto.post.PostDto;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")

//soft delete
@SQLDelete(sql = "UPDATE post SET deleted_at = current_timestamp WHERE id = ?")
@Where(clause = "deleted_at is null")

public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void update(String title, String body){
        this.title = title;
        this.body = body;
    }

    public PostDto postDto(){
        return new PostDto(
                this.id,
                this.title,
                this.body,
                this.user.getUserName(),
                this.getCreatedAt(),
                this.getLastModifiedAt()
        );
    }
}
