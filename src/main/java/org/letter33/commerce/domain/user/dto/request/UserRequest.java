package org.letter33.commerce.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letter33.commerce.domain.user.entity.User;
import org.letter33.commerce.domain.user.entity.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {
    @Getter
    @NoArgsConstructor
    public static class SignUp {
        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, max = 50, message = "이름은 2~50자 사이여야 합니다.")
        String name;

        @NotBlank(message = "별명은 필수입니다.")
        @Size(min = 2, max = 50, message = "별명은 2~50자 사이여야 합니다.")
        String nickname;

        @NotBlank(message = "태그는 필수입니다.")
        @Size(min = 2, max = 10, message = "태그는 2~10자 사이여야 합니다.")
        String tag;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
        private String phone;

        private String gender;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
        private String password;

        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder()
                    .name(name)
                    .nickname(nickname)
                    .tag(tag)
                    .email(email)
                    .phone(phone)
                    .gender(gender)
                    .password(passwordEncoder.encode(password))
                    .role(UserRole.USER)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Login {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }
}
