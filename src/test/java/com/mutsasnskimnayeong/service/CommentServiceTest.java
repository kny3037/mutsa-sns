package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.comment.CommentRequest;
import com.mutsasnskimnayeong.domain.dto.comment.CommentUpdateResponse;
import com.mutsasnskimnayeong.domain.entity.Comment;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.AlarmRepository;
import com.mutsasnskimnayeong.repository.CommentRepository;
import com.mutsasnskimnayeong.repository.PostRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    CommentService commentService;

    CommentRepository commentRepository = mock(CommentRepository.class);
    UserRepository userRepository = mock(UserRepository.class);
    PostRepository postRepository = mock(PostRepository.class);
    AlarmRepository alarmRepository = mock(AlarmRepository.class);

    User user1;
    User user2;
    Post post;
    Comment comment;
    CommentRequest commentRequest;

    @BeforeEach
    void setUp(){
        user1 = User.builder().id(1).userName("test1").build();
        user2 = User.builder().id(1).userName("test2").build();
        post = Post.builder().id(1).title("test").body("test body").user(user1).build();
        comment = Comment.builder().id(1).comment("test comment").user(user1).post(post).build();
        commentRequest = CommentRequest.builder().comment("update comment").build();
    }

    @Test
    @DisplayName("댓글 수정 실패(1) : 포스트 존재하지 않음")
    void update_fail1() {
        Post post1 = mock(Post.class);
        User user = mock(User.class);
        Comment comment1 = mock(Comment.class);

        when(postRepository.findById(post1.getId())).thenThrow(new AppException(ErrorCode.POST_NOT_FOUND,""));

        AppException appException = Assertions.assertThrows(AppException.class,
                () -> commentService.update(post1.getId(),comment1.getId(),commentRequest, user.getUserName()));
        assertEquals(ErrorCode.POST_NOT_FOUND, appException.getErrorCode());

    }

    @Test
    @DisplayName("댓글 수정 실패(2) : 작성자!=유저")
    void update_fail2() {
        when(userRepository.findByUserName(user1.getUserName())).thenReturn(Optional.of(user1));
        when(userRepository.findByUserName(user2.getUserName())).thenReturn(Optional.of(user2));
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        AppException appException = assertThrows(AppException.class, ()-> commentService.update(post.getId(),comment.getId(),commentRequest, user2.getUserName()));
        assertEquals(ErrorCode.INVALID_PERMISSION, appException.getErrorCode());
    }

    @Test
    @DisplayName("댓글 수정 실패(3) : 유저 존재하지 않음")
    void update_fail3() {
        when(userRepository.findByUserName(user1.getUserName())).thenReturn(Optional.of(user1));
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        AppException appException = assertThrows(AppException.class, ()-> commentService.update(post.getId(),comment.getId(),commentRequest, user1.getUserName()));

        assertEquals(ErrorCode.USERNAME_NOT_FOUND, appException.getErrorCode());
    }

    @Test
    @DisplayName("댓글 삭제 실패(1) : 유저 존재하지 않음")
    void delete_fail1() {
        when(userRepository.findByUserName(user1.getUserName())).thenReturn(Optional.of(user1));
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        AppException appException = assertThrows(AppException.class, ()-> commentService.delete(post.getId(),comment.getId(), user1.getUserName()));
        assertEquals(ErrorCode.USERNAME_NOT_FOUND, appException.getErrorCode());
    }

    @Test
    @DisplayName("댓글 삭제 실패(2) : 댓글이 존재하지 않음")
    void delete_fail2() {
        when(userRepository.findByUserName(user1.getUserName())).thenReturn(Optional.of(user1));
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        AppException appException = assertThrows(AppException.class, ()-> commentService.delete(post.getId(),comment.getId(), user1.getUserName()));
        assertEquals(ErrorCode.COMMENT_NOT_FOUND, appException.getErrorCode());
    }
}