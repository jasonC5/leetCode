package com.jason.leetCode;

public class LC2038 {
    public boolean winnerOfGame(String colors) {
        char[] chars = colors.toCharArray();
        int a = 0, b = 0;
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] == 'A' && chars[i - 1] == 'A' && chars[i + 1] == 'A') {
                a++;
            } else if (chars[i] == 'B' && chars[i - 1] == 'B' && chars[i + 1] == 'B') {
                b++;
            }
        }
        return a > b;
    }
}
