package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.UserDto;
import com.mutsasnskimnayeong.domain.dto.UserJoinRequest;
import com.mutsasnskimnayeong.domain.dto.UserLoginRequest;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.UserRepository;
import com.mutsasnskimnayeong.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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

    public String login(UserLoginRequest loginRequest){
        User user = userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(()->new AppException(ErrorCode.USERNAME_NOT_FOUND,ErrorCode.USERNAME_NOT_FOUND.getMessage()));

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage());
        }

        return JwtTokenUtil.createToken(loginRequest.getUserName(),secretKey, expiredTimeMs);

    }

}
