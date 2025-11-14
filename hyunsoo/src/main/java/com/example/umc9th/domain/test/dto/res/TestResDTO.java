package com.example.umc9th.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestResDTO {

    private boolean isSuccess;   // true / false
    private String code;         // COMMON200
    private String message;      // 성공 메시지
    private Testing result;      // 내부 result 객체

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Testing {
        private String testString;   // "This is Test!"
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}