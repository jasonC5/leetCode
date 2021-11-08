package com.jason.leetCode;

public class LC299 {
    public String getHint(String secret, String guess) {
        char[] sec = secret.toCharArray();
        char[] gue = guess.toCharArray();
        int[] secBucket = new int[10];
        int[] gueBucket = new int[10];
        int a = 0;
        for (int i = 0; i < Math.max(sec.length, gue.length); i++) {
            if (i < sec.length && i < gue.length && sec[i] == gue[i]) {
                a++;
            } else {
                if (i < sec.length) {
                    secBucket[sec[i] - '0']++;
                }
                if (i < gue.length) {
                    gueBucket[gue[i] - '0']++;
                }
            }
        }
        int b = 0;
        for (int i = 0; i < 10; i++) {
            b += Math.min(secBucket[i], gueBucket[i]);
        }
        return a+"A"+b+"B";
    }
}
