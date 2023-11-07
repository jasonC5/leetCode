package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/11/7 9:40:07
 * @description
 */
public class LC2586 {
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1))) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return 'a' == c || 'e' == c || 'u' == c || 'i' == c || 'o' == c;
    }
}
