package com.mutsasnskimnayeong.domain.entity;

import com.mutsasnskimnayeong.domain.UserRole;
import com.mutsasnskimnayeong.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;

   @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime updatedAt;
    private LocalDateTime registeredAt;
    private LocalDateTime deletedAt;
}
