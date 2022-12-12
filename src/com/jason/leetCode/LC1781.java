package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/12 10:18:08
 * @description
 */
public class LC1781 {
    public int beautySum(String s) {
        int n = s.length(), ans = 0, max, min;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < s.length(); j++) {
                cnt[s.charAt(j) - 'a']++;
                max = 0;
                min = n;
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        min = Math.min(min, cnt[k]);
                        max = Math.max(max, cnt[k]);
                    }
                }
                ans += max - min;
            }
        }
        return ans;
    }
}
