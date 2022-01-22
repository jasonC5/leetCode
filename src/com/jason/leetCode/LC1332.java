package com.jason.leetCode;

public class LC1332 {
    public int removePalindromeSub(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        return isPalindrome(s) ? 1 : 2;
    }

    private boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length >> 1; i++) {
            if (chars[i] != chars[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
