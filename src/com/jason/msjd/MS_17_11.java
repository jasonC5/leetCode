package com.jason.msjd;

public class MS_17_11 {
    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                idx1 = i;
            } else if (word2.equals(words[i])) {
                idx2 = i;
            }
            if (idx1 != -1 && idx2 != -1) {
                ans = Math.min(ans, Math.abs(idx1 - idx2));
            }
        }
        return ans;
    }
}
