package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.dto.post.*;
import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Api(tags = "포스트")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "글 등록")
    public Response<PostCreateResponse> create(@RequestBody PostCreateRequest createRequest, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        PostDto postDto = postService.create(createRequest, userName);

        return Response.success(new PostCreateResponse("포스트 등록 완료",postDto.getId()));
    }

    @GetMapping("{postId}")
    @Operation(summary = "글 상세")
    public Response<PostDto> detail(@PathVariable Integer postId){
        PostDto postDto = postService.detail(postId);
        return Response.success(postDto);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "글 수정")
    public Response<PostUpdateResponse> update(@PathVariable Integer postId, PostUpdateRequest updateRequest, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        Integer updatePost = postService.update(postId, updateRequest, userName);

        return Response.success(new PostUpdateResponse("포스트 수정 완료", updatePost));
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "글 삭제")
    public Response<PostDeleteResponse> delete(@PathVariable Integer postId, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        Integer deletePost = postService.delete(postId, userName);

        return Response.success(new PostDeleteResponse("포스트 삭제 완료", deletePost));
    }

    @GetMapping
    @Operation(summary = "글 목록")
    public Response<Page<PostDto>> postList(@PageableDefault(sort = "createdAt", size = 20, direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostDto> postDtoPage = postService.postList(pageable);
        return Response.success(postDtoPage);
    }

    @GetMapping("/my")
    @Operation(summary = "나의 글 확인")
    public Response<Page<PostDto>> myFeed(@PageableDefault(sort = "createdAt", size = 20, direction = Sort.Direction.DESC) Pageable pageable, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        Page<PostDto> postDtos = postService.myFeed(userName, pageable);
        return Response.success(postDtos);
    }
}
