package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LC2006 {
    public static int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            ans += countMap.getOrDefault(num - k, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 2, 1, 5, 4};
        int k = 2;
        System.out.println(countKDifference(a, k));
    }

}
