package com.jason.leetCode;

import java.util.*;

public class LC1995 {
    public static int countQuadruplets(int[] nums) {
        Map<Integer, Integer> threeSumCount = new HashMap<>();
        Map<Integer, Integer> twoSumCount = new HashMap<>();
        twoSumCount.put(nums[0] + nums[1], 1);
        int ans = 0;
        for (int i = 2; i < nums.length - 1; i++) {
            for (Map.Entry<Integer, Integer> entry : twoSumCount.entrySet()) {
                int threeSum = entry.getKey() + nums[i];
                // 之前就有的
                Integer count = threeSumCount.getOrDefault(threeSum, 0);
                // 本次新增的
                threeSumCount.put(threeSum, count + entry.getValue());
            }
            // 四元组的第4个元素
            ans += threeSumCount.getOrDefault(nums[i + 1], 0);
            // 补全两个元素之和的统计
            for (int j = 0; j < i; j++) {
                int twoSum = nums[i] + nums[j];
                Integer count = twoSumCount.getOrDefault(twoSum, 0);
                twoSumCount.put(twoSum, ++count);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 5};
        System.out.println(countQuadruplets(nums));
    }
}
