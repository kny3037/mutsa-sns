package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.AlarmType;
import com.mutsasnskimnayeong.domain.dto.comment.CommentRequest;
import com.mutsasnskimnayeong.domain.dto.comment.CommentDto;
import com.mutsasnskimnayeong.domain.dto.comment.CommentResponse;
import com.mutsasnskimnayeong.domain.dto.comment.CommentUpdateResponse;
import com.mutsasnskimnayeong.domain.entity.Alarm;
import com.mutsasnskimnayeong.domain.entity.Comment;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.AlarmRepository;
import com.mutsasnskimnayeong.repository.CommentRepository;
import com.mutsasnskimnayeong.repository.PostRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;

    public CommentDto create(Integer postId, String userName, CommentRequest createRequest){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND,""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Comment comment = commentRepository.save(createRequest.toEntity(user, post));

        alarmRepository.save(Alarm.of(post.getUser(), AlarmType.NEW_COMMENT_ON_POST, user.getId(), post.getId()));

        return comment.dto();
    }

    @Transactional
    public CommentUpdateResponse update(Integer postId, Integer id, CommentRequest createRequest, String userName){
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

        return commentUpdate.updateResponse(commentUpdate);
    }

    @Transactional
    public Integer delete (Integer postId, Integer id, String userName){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.COMMENT_NOT_FOUND,""));

        if (user.getUserName() != comment.getUser().getUserName()){
            throw new AppException(ErrorCode.INVALID_PERMISSION,"");
        }

        commentRepository.deleteById(id);

        return id;
    }

    public Page<Comment> list (Integer postId, Pageable pageable) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new AppException(ErrorCode.POST_NOT_FOUND,""));

        return commentRepository.findAllByPost(post,pageable);

    }


}
