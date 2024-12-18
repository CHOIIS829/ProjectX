package com.insu.backend.global.util;

import java.security.SecureRandom;

public class RandomString8 {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIALS = "@#$%^";
    private static final String ALL = LETTERS + NUMBERS + SPECIALS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length); // 이 객체는 문자열을 더할 때마다 새로운 객체를 생성하는 것이 아니라 기존의 객체에 더하는 방식으로 동작한다.

        // 문자열
        for (int i = 0; i < length; i++) {
            sb.append(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }

        return sb.toString();
    }
}
