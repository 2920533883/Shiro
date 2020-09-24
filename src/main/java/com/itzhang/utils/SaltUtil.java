package com.itzhang.utils;

import java.util.Random;

public class SaltUtil {
    private static final char[] key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    public static String getSalt(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(key[new Random().nextInt(key.length)]);
        }
        return stringBuilder.toString();
    }
}
