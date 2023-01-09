package com.mutsasnskimnayeong.service;

import com.mutsasnskimnayeong.domain.dto.alarm.AlarmResponse;
import com.mutsasnskimnayeong.domain.entity.Alarm;
import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.AppException;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.repository.AlarmRepository;
import com.mutsasnskimnayeong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public Page<AlarmResponse> alarmList (String userName, Pageable pageable){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        Page<Alarm> alarms = alarmRepository.findByUserId(user.getId(), pageable);
        Page<AlarmResponse> responsePage = AlarmResponse.response(alarms);
        return responsePage;
    }

}
