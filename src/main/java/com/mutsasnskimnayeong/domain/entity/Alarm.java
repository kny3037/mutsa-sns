package com.mutsasnskimnayeong.domain.entity;

import com.mutsasnskimnayeong.domain.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alarm extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    private Integer fromUserId;
    private Integer targetId;
    private String text;

    public static Alarm of(User user, AlarmType alarmType, Integer fromUserId, Integer targetId){
        return Alarm.builder()
                .user(user)
                .alarmType(alarmType)
                .fromUserId(fromUserId)
                .targetId(targetId)
                .text(alarmType.getAlarmText())
                .build();
    }
}
