package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.domain.dto.alarm.AlarmResponse;
import com.mutsasnskimnayeong.domain.response.Response;
import com.mutsasnskimnayeong.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    public Response<Page<AlarmResponse>> list(@PageableDefault(sort = "createdAt", size = 20, direction = Sort.Direction.DESC) Pageable pageable, @ApiIgnore Authentication authentication){
        String userName = authentication.getName();
        Page<AlarmResponse> responsePage = alarmService.alarmList(userName, pageable);
        return Response.success(responsePage);
    }

}
