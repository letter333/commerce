package org.letter33.commerce.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.letter33.commerce.domain.user.entity.User;
import org.letter33.commerce.domain.user.entity.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserInfo {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private int age;
    private String gender;
    private UserRole role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserInfo from(User user) {
        return UserInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthdate(user.getBirthdate())
                .age(user.getAge())
                .gender(user.getGender())
                .role(user.getRole())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UserInfo publicFrom(User user) {
        return UserInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .age(user.getAge())
                .build();
    }
}
