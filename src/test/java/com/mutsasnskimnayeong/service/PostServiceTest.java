package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.post.PostCreateRequest;
import com.mutsasnskimnayeong.domain.dto.post.PostDto;
import com.mutsasnskimnayeong.domain.dto.post.PostUpdateRequest;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.PostRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceTest {

    PostService postService;

    PostRepository postRepository = mock(PostRepository.class);
    UserRepository userRepository = mock(UserRepository.class);

    @BeforeEach
    void setUp(){
        postService = new PostService(postRepository, userRepository);
    }

    @Test
    @DisplayName("등록 성공")
    void create_success() {

        Post post = mock(Post.class);
        User user = mock(User.class);

        PostCreateRequest createRequest = PostCreateRequest.builder()
                .title("post test")
                .body("post create test")
                .build();

        when(postRepository.save(any())).thenReturn(post);
        when(userRepository.findByUserName(user.getUserName())).thenReturn(Optional.of(user));

        Assertions.assertDoesNotThrow(()-> postService.create(new PostCreateRequest(createRequest.getTitle(), createRequest.getBody()), user.getUserName()));
    }

    @Test
    @DisplayName("등록 실패 - 유저가 존재하지 않을 때")
    void create_fail() {

        Post post = mock(Post.class);
        User user = mock(User.class);

        PostCreateRequest createRequest = PostCreateRequest.builder()
                .title("post test")
                .body("post create test")
                .build();

        when(postRepository.save(any())).thenReturn(post);
        when(userRepository.findByUserName(user.getUserName())).thenReturn(Optional.empty());

        AppException appException = assertThrows(AppException.class,()-> postService.create(new PostCreateRequest(createRequest.getTitle(), createRequest.getBody()), user.getUserName()));

        assertEquals(ErrorCode.USERNAME_NOT_FOUND, appException.getErrorCode());
    }

    @Test
    @DisplayName("조회 성공")
    void detail_success() {
        User user = User.builder()
                .id(1)
                .userName("kny")
                .password("0000")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("Test title")
                .body("Test body")
                .user(user)
                .build();

        when(postRepository.findById(1)).thenReturn(Optional.of(post));

        PostDto postDto = postService.detail(post.getId());
        assertEquals(user.getUserName(), postDto.getUserName());
    }

    @Test
    @DisplayName("수정 실패 - 포스트 존재하지 않음")
    void update_fail1() {
        Post post = mock(Post.class);
        User user = mock(User.class);

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post test")
                .body("post update test")
                .build();

        when(postRepository.findById(post.getId())).thenReturn(Optional.empty());

        AppException appException = Assertions.assertThrows(AppException.class,
                ()-> postService.update(post.getId(), updateRequest, user.getUserName()));

        assertEquals(ErrorCode.POST_NOT_FOUND, appException.getErrorCode());
    }

    @Test
    @DisplayName("수정 실패 - 작성자!=유저")
    void update_fail2() {
        User user = User.builder()
                .id(1)
                .userName("kny")
                .password("0000")
                .build();
        User user2 = User.builder()
                .id(1)
                .userName("kny2")
                .password("1111")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("Test title")
                .body("Test body")
                .user(user)
                .build();

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post test")
                .body("post update test")
                .build();

        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(userRepository.findByUserName(user.getUserName())).thenReturn(Optional.of(user));
        when(userRepository.findByUserName(user2.getUserName())).thenReturn(Optional.of(user2));

        AppException appException = assertThrows(AppException.class,
                ()-> postService.update(post.getId(), updateRequest, user2.getUserName()));

        assertEquals(ErrorCode.INVALID_PERMISSION, appException.getErrorCode());
    }

    @Test
    @DisplayName("수정 실패 - 포스트 유저 존재하지 않음")
    void update_fail3() {
        User user = User.builder()
                .id(1)
                .userName("kny")
                .password("0000")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("Test title")
                .body("Test body")
                .user(user)
                .build();

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post test")
                .body("post update test")
                .build();

        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(userRepository.findByUserName(user.getUserName())).thenReturn(Optional.of(user));

        AppException appException = assertThrows(AppException.class,
                ()-> postService.update(post.getId(), updateRequest, "user2"));

        assertEquals(ErrorCode.USERNAME_NOT_FOUND, appException.getErrorCode());
    }

    @Test
    @DisplayName("삭제 실패 - 유저 존재하지 않음")
    void delete_fail1() {
        User user = User.builder()
                .id(1)
                .userName("kny")
                .password("0000")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("Test title")
                .body("Test body")
                .user(user)
                .build();

        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(userRepository.findByUserName(user.getUserName())).thenReturn(Optional.empty());

        AppException appException = assertThrows(AppException.class,
                ()-> postService.delete(post.getId(), user.getUserName()));

        assertEquals(ErrorCode.USERNAME_NOT_FOUND, appException.getErrorCode());
    }

    @Test
    @DisplayName("삭제 실패 - 포스트 존재하지 않음")
    void delete_fail2() {
        User user = User.builder()
                .id(1)
                .userName("kny")
                .password("0000")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("Test title")
                .body("Test body")
                .user(user)
                .build();

        when(postRepository.findById(post.getId())).thenReturn(Optional.empty());

        AppException appException = assertThrows(AppException.class,
                ()-> postService.delete(post.getId(), user.getUserName()));

        assertEquals(ErrorCode.POST_NOT_FOUND, appException.getErrorCode());

    }

    @Test
    @DisplayName("삭제 실패 - 작성자와 유저가 존재하지 않음")
    void delete_fail3() {
        User user = User.builder()
                .id(1)
                .userName("kny")
                .password("0000")
                .build();

        User user2 = User.builder()
                .id(1)
                .userName("kny2")
                .password("1111")
                .build();

        Post post = Post.builder()
                .id(1)
                .title("Test title")
                .body("Test body")
                .user(user)
                .build();

        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        when(userRepository.findByUserName(user2.getUserName())).thenReturn(Optional.of(user2));

        AppException appException = assertThrows(AppException.class,
                () -> postService.delete(post.getId(), user2.getUserName()));

        assertEquals(ErrorCode.INVALID_PERMISSION, appException.getErrorCode());

    }

}
