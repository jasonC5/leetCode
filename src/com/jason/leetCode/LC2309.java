package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/27 10:36:21
 * @description
 */
public class LC2309 {
    public static String greatestLetter(String s) {
        int low = 0, cap = 0;
        char ans = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if ('a' <= c && c <= 'z') {
                int seek = c - 'a';
                if ((1 & (cap >> seek)) == 1 && c + ('A' - 'a') > ans) {
                    ans = (char) (c + 'A' - 'a');
                }
                low |= (1 << seek);
            } else {
                int seek = c - 'A';
                if ((1 & (low >> seek)) == 1 && c > ans) {
                    ans = c;
                }
                cap |= (1 << seek);
            }
        }
        return ans == 0 ? "" : ans + "";
    }

    public static void main(String[] args) {
        String s = "lEeTcOdE";
        System.out.println(greatestLetter(s));
    }
}
