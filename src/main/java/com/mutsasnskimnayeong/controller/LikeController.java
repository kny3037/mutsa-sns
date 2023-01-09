package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Api(tags = "좋아요")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/likes")
    @Operation(summary = "좋아요 등록")
    public Response<String> like(@PathVariable Integer postId, @ApiIgnore Authentication authentication){

        String userName = authentication.getName();
        likeService.like(postId, userName);
        return Response.success("좋아요를 눌렀습니다.");
    }

    @GetMapping("/{postId}/likes")
    @Operation(summary = "좋아요 개 수 확인")
    public Response<Integer> likeCount(@PathVariable Integer postId){
        Integer likeCount = likeService.likeCount(postId);
        return Response.success(likeCount);
    }
}


