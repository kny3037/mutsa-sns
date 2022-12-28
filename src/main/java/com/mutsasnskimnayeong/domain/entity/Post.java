package com.mutsasnskimnayeong.domain.entity;

import com.mutsasnskimnayeong.domain.dto.PostDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
