package com.example.umc9th.domain.review.enums;

public enum ReviewEnum {
    ALL, FIVE, FOUR, THREE, TWO, ONE;

    public static ReviewEnum from(String v) {
        if (v == null) return ALL;
        try { return ReviewEnum.valueOf(v.toUpperCase()); }
        catch (Exception e) { return ALL; }
    }
}
