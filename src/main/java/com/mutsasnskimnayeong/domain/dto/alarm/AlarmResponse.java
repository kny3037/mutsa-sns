package com.mutsasnskimnayeong.domain.dto.alarm;

import com.mutsasnskimnayeong.domain.AlarmType;
import com.mutsasnskimnayeong.domain.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmResponse {
    private Integer id;
    private AlarmType alarmType;
    private Integer fromUserId;
    private Integer targetId;
    private String text;
    private LocalDateTime createdAt;

    public static Page<AlarmResponse> response(Page<Alarm> alarms){
        Page<AlarmResponse> responsePage = alarms.map(alarm -> AlarmResponse.builder()
                .id(alarm.getId())
                .alarmType(alarm.getAlarmType())
                .fromUserId(alarm.getFromUserId())
                .targetId(alarm.getTargetId())
                .text(alarm.getText())
                .createdAt(alarm.getCreatedAt())
                .build());
        return responsePage;
    }
}
