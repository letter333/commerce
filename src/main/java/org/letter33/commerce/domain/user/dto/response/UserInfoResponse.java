package org.letter33.commerce.domain.user.dto.response;

import lombok.Builder;
import org.letter33.commerce.domain.user.entity.UserRole;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record UserInfoResponse(
        Long id,
        String name,
        String nickname,
        String tag,
        String email,
        String phone,
        int age,
        Date birthdate,
        String gender,
        UserRole role,
        boolean active,
        boolean deleteFlag,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt
) {

}
