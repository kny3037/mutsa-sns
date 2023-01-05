package com.mutsasnskimnayeong.repository;

import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAllByUser (User user, Pageable pageable);
}
