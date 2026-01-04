package org.letter33.commerce.domain.user.dto.response;

import lombok.Builder;
import org.letter33.commerce.domain.user.entity.User;

@Builder
public record SignUpResponse(
        Long id,
        String name,
        String nickname,
        String tag,
        String email,
        String message
) {
    public static SignUpResponse from(User user) {
        return SignUpResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .tag(user.getTag())
                .email(user.getEmail())
                .message("회원가입이 완료되었습니다.")
                .build();
    }
}
