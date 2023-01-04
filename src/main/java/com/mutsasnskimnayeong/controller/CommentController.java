package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.dto.comment.CommentRequest;
import com.mutsasnskimnayeong.domain.dto.comment.CommentResponse;
import com.mutsasnskimnayeong.domain.dto.comment.CommentDto;
import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public Response<CommentResponse> create(@PathVariable Integer postId, @RequestBody CommentRequest createRequest, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        CommentDto commentDto = commentService.create(postId,userName,createRequest);

        return Response.success(commentDto.response());
    }

    @PutMapping("/{postId}/comments/{id}")
    public Response<CommentResponse> update(@PathVariable Integer postId, @PathVariable Integer id, @RequestBody CommentRequest createRequest, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        CommentDto updateComment = commentService.update(postId, id, createRequest, userName);

        return Response.success(updateComment.response());
    }



}
