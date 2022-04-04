package com.example.demo.utils;

public class utils {
    public static boolean isPalindrome(String value) {
        StringBuilder builder = new StringBuilder(value);
        String reverseName = builder.reverse().toString();
        return value.equals(reverseName);

    }

}
