package com.umc.prac.global.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 1;
    }
}

