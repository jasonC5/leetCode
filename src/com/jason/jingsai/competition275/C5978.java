package com.jason.jingsai.competition275;

import java.util.HashSet;
import java.util.Set;

public class C5978 {
    public static int wordCount(String[] startWords, String[] targetWords) {
        int length = startWords.length;
        Set<Integer> bitmaps = new HashSet<>();
        for (int i = 0; i < length; i++) {
            char[] chars = startWords[i].toCharArray();
            int bit = 0;
            for (char c : chars) {
                bit |= 1 << (c - 'a');
            }
            bitmaps.add(bit);
        }
        int ans = 0;
        for (String word : targetWords) {
            char[] chars = word.toCharArray();
            int bit = 0;
            for (char c : chars) {
                bit |= 1 << (c - 'a');
            }
            for (int i = 0; i < 26; i++) {
                if ((bit & (1 << i)) != 0) {
                    int key = bit & ~(1 << i);
                    if (bitmaps.contains(key)) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] startWords = {"ant", "act", "tack"}, targetWords = {"tack", "act", "acti"};
        System.out.println(wordCount(startWords, targetWords));
    }
}
