package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.post.PostCreateRequest;
import com.mutsasnskimnayeong.domain.dto.post.PostDto;
import com.mutsasnskimnayeong.domain.dto.post.PostUpdateRequest;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.PostRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto create(PostCreateRequest createRequest, String userName){

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Post post = postRepository.save(createRequest.toEntity(user));

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    public PostDto detail(Integer id){

        Post post = postRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND,""));

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUser().getUserName())
                .createAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();
    }


    @Transactional
    public Integer update(Integer id, PostUpdateRequest updateRequest, String userName){

        Post post = postRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        if (post.getUser().getUserName() != user.getUserName()){
            throw new AppException(ErrorCode.INVALID_PERMISSION, "");
        }

        post.update(updateRequest.getTitle(), updateRequest.getBody());
        postRepository.save(post);

        return id;
    }

    @Transactional
    public Integer delete(Integer id, String userName){

        Post post = postRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND, ""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND, ""));
        if (post.getUser().getUserName() != user.getUserName()){
            throw new AppException(ErrorCode.INVALID_PERMISSION, "");
        }

        postRepository.deleteById(id);

        return id;
    }

    public Page<PostDto> postList(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostDto> postDtos = PostDto.postDto(posts);
        return postDtos;
    }

    public Page<PostDto> myFeed(String userName, Pageable pageable){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Page<Post> posts = postRepository.findAllByUser(user, pageable);

        return posts.map(post -> post.postDto());
    }
}
