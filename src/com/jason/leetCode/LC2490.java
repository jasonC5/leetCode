package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/6/30 11:18:12
 * @description
 */
public class LC2490 {
    public boolean isCircularSentence(String sentence) {
        String[] str = sentence.split(" ");
        int n = str.length;
        if (str[0].charAt(0) != str[n - 1].charAt(str[n - 1].length() - 1)) {
            return false;
        }
        for (int i = 1; i < n; i++) {
            if (str[i].charAt(0) != str[i - 1].charAt(str[i - 1].length() - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCircularSentence2(String sentence) {
        int n = sentence.length();
        if (sentence.charAt(0) != sentence.charAt(n - 1)) {
            return false;
        }
        for (int i = 1; i < n - 1; i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
