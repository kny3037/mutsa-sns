package com.mutsasnskimnayeong.domain.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"likes\"")
@SQLDelete(sql = "UPDATE \"likes\" SET delete_at = current_timestamp where id = ?")
@Where(clause = "deleted_at is null")
public class Like extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime deleteAt;

    public static Like of(User user, Post post){
        return Like.builder()
                .user(user)
                .post(post)
                .build();
    }


}
