package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}/likes")
    public Response<String> like(@PathVariable Integer postId, @ApiIgnore Authentication authentication){

        String userName = authentication.getName();
        likeService.like(postId, userName);
        return Response.success("좋아요를 눌렀습니다.");
    }

    @GetMapping("/{postId}/likes")
    public Response<Integer> likeCount(@PathVariable Integer postId){
        Integer likeCount = likeService.likeCount(postId);
        return Response.success(likeCount);
    }
}


