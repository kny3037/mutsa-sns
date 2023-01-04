package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.comment.CommentRequest;
import com.mutsasnskimnayeong.domain.dto.comment.CommentDto;
import com.mutsasnskimnayeong.domain.entity.Comment;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.CommentRepository;
import com.mutsasnskimnayeong.repository.PostRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentDto create(Integer postId, String userName, CommentRequest createRequest){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND,""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Comment comment = commentRepository.save(createRequest.toEntity(user, post));

        return comment.dto();
    }

    @Transactional
    public CommentDto update(Integer postId, Integer id, CommentRequest createRequest, String userName){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND,""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND, ""));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.COMMENT_NOT_FOUND,""));


        if (user.getUserName() != comment.getUser().getUserName()){
            throw new AppException(ErrorCode.INVALID_PERMISSION,"");
        }

        comment.update(createRequest.getComment());
        Comment commentUpdate = commentRepository.save(comment);

        return commentUpdate.dto();
    }



}
