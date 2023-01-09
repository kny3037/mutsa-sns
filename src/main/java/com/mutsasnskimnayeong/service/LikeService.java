package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.AlarmType;
import com.mutsasnskimnayeong.domain.entity.Alarm;
import com.mutsasnskimnayeong.domain.entity.Like;
import com.mutsasnskimnayeong.domain.entity.Post;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.AlarmRepository;
import com.mutsasnskimnayeong.repository.LikeRepository;
import com.mutsasnskimnayeong.repository.PostRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final AlarmRepository alarmRepository;


    @Transactional
    public void like(Integer postId, String userName){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,""));

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Optional<Like> optionalLike = likeRepository.findByUserAndPost(user, post);

        //좋아요를 이미 눌렀으면
        if (optionalLike.isPresent()){
            likeRepository.delete(optionalLike.get());
            throw new AppException(ErrorCode.ALREADY_LIKED,"");
        }else {
            //좋아요 안눌렀으면 save
            likeRepository.save(Like.of(user, post));
            alarmRepository.save(Alarm.of(post.getUser(), AlarmType.NEW_LIKE_ON_POST, user.getId(), post.getId()));
        }
    }

    public Integer likeCount(Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,""));

        return likeRepository.countByPost(post);
    }

}
