package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.dto.UserDto;
import com.mutsasnskimnayeong.domain.dto.UserJoinRequest;
import com.mutsasnskimnayeong.domain.dto.UserJoinResponse;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest joinRequest){
        UserDto userDto = userService.join(joinRequest);
        return Response.success(new UserJoinResponse(userDto.getId(), userDto.getPassword()));
    }
}
