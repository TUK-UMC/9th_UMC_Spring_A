package com.umc.prac.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StoreCreateRequest(
        @NotBlank(message = "가게 이름은 필수입니다.")
        String name,

        @NotBlank(message = "가게 연락처는 필수입니다.")
        String managerNumber
) {
}

