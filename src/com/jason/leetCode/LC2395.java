package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2023/3/26 15:22:01
 * @description
 */
public class LC2395 {
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> exist = new HashSet<>();
        for (int i = 1; i < n; i++) {
            int sum = nums[i - 1] + nums[i];
            if (exist.contains(sum)){
                return true;
            }
            exist.add(sum);
        }
        return false;
    }
}
