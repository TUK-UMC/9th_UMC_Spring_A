package com.example.umc9th.global.validation.validator;

import com.example.umc9th.global.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null 체크
        if (value == null) {
            return false;
        }
        
        // 1 이상인지 확인
        return value >= 1;
    }
}
