package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] source = statistics(magazine);
        int[] target = statistics(ransomNote);
        for (int i = 0; i < 26; i++) {
            if (target[i] > source[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] statistics(String str) {
        int[] count = new int[26];
        char[] chars = str.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        return count;
    }

}
