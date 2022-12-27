package com.mutsasnskimnayeong.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutsasnskimnayeong.domain.dto.UserDto;
import com.mutsasnskimnayeong.domain.dto.UserJoinRequest;
import com.mutsasnskimnayeong.domain.dto.UserLoginRequest;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join_success() throws Exception{

        UserJoinRequest joinRequest = UserJoinRequest.builder()
                .userName("testuser")
                .password("1111")
                .build();

        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(joinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복인 경우")
    @WithMockUser
    void join_fail() throws Exception{

        UserJoinRequest joinRequest = UserJoinRequest.builder()
                .userName("testuser")
                .password("1111")
                .build();

        when(userService.join(any())).thenThrow(new AppException(ErrorCode.DUPLICATED_USER_NAME,""));


        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(joinRequest)))
                .andDo(print())
                .andExpect(status().is(ErrorCode.DUPLICATED_USER_NAME.getStatus().value()));
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception{

        UserLoginRequest loginRequest = UserLoginRequest.builder()
                .userName("testuser")
                .password("1111")
                .build();

        when(userService.login(any())).thenReturn("token");

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").exists())
                .andExpect(jsonPath("$.result.jwt").exists());

    }

    @Test
    @DisplayName("로그인 실패 - userName 없음")
    @WithMockUser
    void login_fail() throws Exception{

        UserLoginRequest loginRequest = UserLoginRequest.builder()
                .userName("testuser")
                .password("1111")
                .build();

        when(userService.login(any())).thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    void login_fail2() throws Exception{

        UserLoginRequest loginRequest = UserLoginRequest.builder()
                .userName("testuser")
                .password("1111")
                .build();

        when(userService.login(any())).thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));


        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}