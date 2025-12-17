package com.umc.prac.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberJoinRequest(
        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하이어야 합니다.")
        String password
) {
}


