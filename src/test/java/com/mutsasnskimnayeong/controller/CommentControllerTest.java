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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    Page<CommentResponse> commentResponses;


    @BeforeEach
    void setUp(){
        commentRequest = CommentRequest.builder()
                .comment("comment test?????????.")
                .build();

        updateResponse = CommentUpdateResponse.builder()
                .id(1)
                .postId(1)
                .comment(commentRequest.getComment())
                .build();
    }

    @Test
    @DisplayName("?????? ?????? ??????")
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
    @DisplayName("?????? ?????? ??????(1) - ????????? ?????? ?????? ??????")
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
    @DisplayName("?????? ?????? ??????(2) - ???????????? ???????????? ?????? ??????")
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
    @DisplayName("?????? ?????? ??????")
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
    @DisplayName("?????? ?????? ??????(1) : ?????? ??????")
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
    @DisplayName("?????? ?????? ??????(2) : Post ?????? ??????")
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
    @DisplayName("?????? ?????? ??????(3) : ????????? ?????????")
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
    @DisplayName("?????? ?????? ??????(4) : ?????????????????? ??????")
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
    @DisplayName("?????? ?????? ??????")
    @WithMockUser
    void delete_success() throws Exception {

        mockMvc.perform(delete("/api/v1/posts/1/comments/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("?????? ?????? ??????(1) : ?????? ??????")
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
    @DisplayName("?????? ?????? ??????(2) : Post?????? ??????")
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
    @DisplayName("?????? ?????? ??????(3) : ????????? ?????????")
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
    @DisplayName("?????? ?????? ??????(4) : ?????????????????? ??????")
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

    @Test
    @DisplayName("?????? ?????? ?????? ??????")
    @WithMockUser
    void list_success() throws Exception {

        when(commentService.list(any(),any())).thenReturn(Page.empty());

        mockMvc.perform(get("/api/v1/posts/1/comments")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
                .andExpect(jsonPath("$.result.pageable").exists())
                .andDo(print());

    }

}