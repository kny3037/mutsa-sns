package com.mutsasnskimnayeong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasnskimnayeong.domain.dto.*;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    // 포스트 작성
    @Test
    @DisplayName("포스트 작성 성공")
    @WithMockUser
    void create_success() throws Exception{

        PostCreateRequest createRequest = PostCreateRequest.builder()
                .title("post test")
                .body("post create test")
                .build();

        when(postService.create(any(), any())).thenReturn(PostDto.builder().id(1).title("post test").body("post create test").build());

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 작성 실패 - JWT를 Bearer Token으로 보내지 않은 경우")
    @WithMockUser
    void create_fail() throws Exception {
        PostCreateRequest createRequest = PostCreateRequest.builder()
                .title("post test")
                .body("post create test")
                .build();

        when(postService.create(any(),any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createRequest)))
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()))
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 작성 실패 - JWT가 유효하지 않은 경우")
    @WithMockUser
    void create_fail2() throws Exception{
        PostCreateRequest createRequest = PostCreateRequest.builder()
                .title("post test")
                .body("post create test")
                .build();

        when(postService.create(any(),any())).thenThrow(new AppException(ErrorCode.INVALID_TOKEN,""));

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createRequest)))
                .andExpect(status().is(ErrorCode.INVALID_TOKEN.getStatus().value()))
                .andDo(print());

    }

    //포스트 상세

    @Test
    @DisplayName("포스트 상세 조회 성공")
    @WithMockUser
    void detail_success() throws Exception {

        PostDto postDto = PostDto.builder()
                .id(1)
                .title("hi")
                .body("welcome")
                .userName("kny")
                .build();

        when(postService.detail(any())).thenReturn(postDto);

        mockMvc.perform(get("/api/v1/posts/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // 포스트 수정
    @Test
    @DisplayName("포스트 수정 성공")
    @WithMockUser
    void update_success() throws Exception {

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post update")
                .body("post update body")
                .build();

        Post post = Post.builder()
                .id(1)
                .build();

        when(postService.update(any(), any(), any())).thenReturn(post.getId());

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 수정 실패 - 인증 실패")
    @WithMockUser
    void update_fail1() throws Exception {

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post update")
                .body("post update body")
                .build();

        when(postService.update(any(),any(),any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateRequest)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 수정 실패 - 작성자 불일치")
    @WithMockUser
    void update_fail2() throws Exception {

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post update")
                .body("post update body")
                .build();

        when(postService.update(any(),any(),any())).thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateRequest)))
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()))
                .andDo(print());
    }
    @Test
    @DisplayName("포스트 수정 실패 - 데이터베이스 에러")
    @WithMockUser
    void update_fail3() throws Exception {

        PostUpdateRequest updateRequest = PostUpdateRequest.builder()
                .title("post update")
                .body("post update body")
                .build();

        when(postService.update(any(),any(),any())).thenThrow(new AppException(ErrorCode.DATABASE_ERROR,""));

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateRequest)))
                .andExpect(status().is(ErrorCode.DATABASE_ERROR.getStatus().value()))
                .andDo(print());
    }

    //포스트 삭제
    @Test
    @DisplayName("포스트 삭제 성공")
    @WithMockUser
    void delete_success() throws Exception {

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 삭제 실패 - 인증 실패")
    @WithMockUser
    void delete_fail1() throws Exception {

        when(postService.delete(any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 삭제 실패 - 작성자 불일치")
    @WithMockUser
    void delete_fail2() throws Exception {

        when(postService.delete(any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()))
                .andDo(print());
    }

    @Test
    @DisplayName("포스트 삭제 실패 - 데이터베이스 에러")
    @WithMockUser
    void delete_fail3() throws Exception {

        when(postService.delete(any(),any()))
                .thenThrow(new AppException(ErrorCode.DATABASE_ERROR,""));

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(ErrorCode.DATABASE_ERROR.getStatus().value()))
                .andDo(print());
    }

    // 포스트 리스트 조회
    // 0번이 1번보다 날짜가 최신일 경우?
    @Test
    @DisplayName("포스트 리스트 조회 성공")
    @WithMockUser
    void list_success() throws Exception {

        mockMvc.perform(get("/api/v1/posts")
                        .param("sort", "createdAt,desc"))
                .andExpect(status().isOk())
                .andDo(print());

        ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(postService).postList(pageableArgumentCaptor.capture());
        PageRequest request = (PageRequest) pageableArgumentCaptor.getValue();

        assertEquals(Sort.by(DESC, "createdAt"), request.getSort());




    }

}