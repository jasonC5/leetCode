package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JasonC5
 */
public class LC1218 {

    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> cache = new HashMap<>();// 以key结尾，最长等差子序列的长度
        int max = 0;
        for (int num : arr) {
            int length = cache.getOrDefault(num - difference, 0) + 1;
            max = Math.max(max, length);
            cache.put(num, length);
        }
        return max;
    }

}
