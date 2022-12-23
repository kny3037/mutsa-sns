package com.mutsasnskimnayeong.repository;

import com.mutsasnskimnayeong.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
