package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.UserDto;
import com.mutsasnskimnayeong.domain.dto.UserJoinRequest;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private Long expiredTimeMs = 1000 * 60 * 60l;

    public UserDto join(UserJoinRequest joinRequest){
        userRepository.findByUserName(joinRequest.getUserName()).ifPresent(user -> {
            throw new AppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName %s is duplicated", joinRequest.getUserName()));
        });

        User user = userRepository.save(joinRequest.toEntity(encoder.encode(joinRequest.getPassword())));
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }
}
