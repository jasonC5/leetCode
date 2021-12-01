package com.jason.leetCode;

public class LC1446 {
    public int maxPower(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int curLen = 1;
        char curChar = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (curChar == chars[i]) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
                curChar = chars[i];
            }
        }
        // 最后一个可能是答案，但是上面没比较
        return Math.max(maxLen, curLen);
    }
}
