package com.umc.study.api.store.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private String content;
    private Float rating;
}