package com.mutsasnskimnayeong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasnskimnayeong.domain.dto.comment.CommentDto;
import com.mutsasnskimnayeong.domain.dto.comment.CommentRequest;
import com.mutsasnskimnayeong.domain.dto.comment.CommentResponse;
import com.mutsasnskimnayeong.domain.dto.comment.CommentUpdateResponse;
import com.mutsasnskimnayeong.domain.entity.Comment;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CommentService commentService;

    CommentRequest commentRequest;
    CommentUpdateResponse updateResponse;


    @BeforeEach
    void setUp(){
        commentRequest = CommentRequest.builder()
                .comment("comment test입니다.")
                .build();

        updateResponse = CommentUpdateResponse.builder()
                .id(1)
                .postId(1)
                .comment(commentRequest.getComment())
                .build();
    }

    @Test
    @DisplayName("댓글 작성 성공")
    @WithMockUser
    void create_success() throws Exception {

        when(commentService.create(any(),any(),any()))
                .thenReturn(mock(CommentDto.class));

        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("댓글 작성 실패(1) - 로그인 하지 않은 경우")
    @WithAnonymousUser
    void create_fail1() throws Exception {

        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().isUnauthorized())
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 작성 실패(2) - 게시물이 존재하지 않는 경우")
    @WithMockUser
    void create_fail2() throws Exception{

        when(commentService.create(any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND,""));

        mockMvc.perform(post("/api/v1/posts/1/comments")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getStatus().value()))
                .andDo(print());
    }


    @Test
    @DisplayName("댓글 수정 성공")
    @WithMockUser
    void update_success() throws Exception {

        when(commentService.update(any(),any(),any(),any()))
                .thenReturn(updateResponse);

        mockMvc.perform(put("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("댓글 수정 실패(1) : 인증 실패")
    @WithAnonymousUser
    void update_fail1() throws Exception {

        when(commentService.update(any(),any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(put("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()))
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 수정 실패(2) : Post 없는 경우")
    @WithMockUser
    void update_fail2() throws Exception {

        when(commentService.update(any(),any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND,""));

        mockMvc.perform(put("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getStatus().value()))
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 수정 실패(3) : 작성자 불일치")
    @WithMockUser
    void update_fail3() throws Exception {

        when(commentService.update(any(),any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(put("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()))
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 수정 실패(4) : 데이터베이스 에러")
    @WithMockUser
    void update_fail4() throws Exception {

        when(commentService.update(any(),any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.DATABASE_ERROR,""));

        mockMvc.perform(put("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(commentRequest)))
                .andExpect(status().is(ErrorCode.DATABASE_ERROR.getStatus().value()))
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 삭제 성공")
    @WithMockUser
    void delete_success() throws Exception {

        mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 삭제 실패(1) : 인증 실패")
    @WithMockUser
    void delete_fail1() throws Exception {

        when(commentService.delete(any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());


    }

    @Test
    @DisplayName("댓글 삭제 실패(2) : Post없는 경우")
    @WithMockUser
    void delete_fail2() throws Exception {

        when(commentService.delete(any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.POST_NOT_FOUND,""));

        mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(ErrorCode.POST_NOT_FOUND.getStatus().value()))
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 삭제 실패(3) : 작성자 불일치")
    @WithMockUser
    void delete_fail3() throws Exception {

        when(commentService.delete(any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PERMISSION,""));

        mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()))
                .andDo(print());
    }

    @Test
    @DisplayName("댓글 삭제 실패(4) : 데이터베이스 에러")
    @WithMockUser
    void delete_fail4() throws Exception {

        when(commentService.delete(any(),any(),any()))
                .thenThrow(new AppException(ErrorCode.DATABASE_ERROR,""));

        mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(ErrorCode.DATABASE_ERROR.getStatus().value()))
                .andDo(print());
    }

   /* @Test
    @DisplayName("댓글 목록 조회 성공")
    @WithMockUser
    void list_success() throws Exception {
    }*/

}