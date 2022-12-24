package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.dto.PostCreateRequest;
import com.mutsasnskimnayeong.domain.dto.PostCreateResponse;
import com.mutsasnskimnayeong.domain.dto.PostDto;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<PostCreateResponse> create(@RequestBody PostCreateRequest createRequest, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        PostDto postDto = postService.create(createRequest, userName);

        return Response.success(new PostCreateResponse("포스트 등록 완료",postDto.getId()));
    }

    @GetMapping("{postId}")
    public Response<PostDto> detail(@PathVariable Integer postId){
        PostDto postDto = postService.detail(postId);
        return Response.success(postDto);
    }
}
