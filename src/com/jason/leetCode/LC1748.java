package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LC1748 {
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (!countMap.containsKey(num)) {
                ans += num;
                countMap.put(num, 1);
            } else {
                Integer count = countMap.get(num);
                if (count == 1) {
                    ans -= num;
                    countMap.put(num, count + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1};
        System.out.println(new LC1748().sumOfUnique(nums));
    }
}
