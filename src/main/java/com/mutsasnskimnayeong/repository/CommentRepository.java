package com.mutsasnskimnayeong.repository;

import com.mutsasnskimnayeong.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
