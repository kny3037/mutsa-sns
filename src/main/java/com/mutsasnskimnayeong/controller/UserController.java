package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.dto.user.*;
import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Api(tags = "회원가입 및 로그인")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest joinRequest){
        UserDto userDto = userService.join(joinRequest);
        return Response.success(new UserJoinResponse(userDto.getId(), userDto.getUserName()));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest){
        String token = userService.login(loginRequest);
        return Response.success(new UserLoginResponse(token));
    }
}
