package com.mutsasnskimnayeong.repository;

import com.mutsasnskimnayeong.domain.entity.Like;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    Optional<Like> findByUserAndPost(User user, Post post);
    List<Like> findAllByPost(Post post);

    Integer countByPost(Post post);

}
