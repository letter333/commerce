package org.letter33.commerce.domain.user.dto.response;

import org.letter33.commerce.domain.user.dto.UserInfo;
import org.letter33.commerce.domain.user.entity.User;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long expiresIn,
        UserInfo userInfo
) {
    public static LoginResponse of(String accessToken, Long expiresIn, User user) {
        return new LoginResponse(
                accessToken,
                "Bearer",
                expiresIn,
                UserInfo.from(user)
        );
    }
}
