package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2022/8/9 16:40:23
 * @description
 */
public class LC2367 {
    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int num : nums) {
            set.add(num);
            if (set.contains(num - diff) && set.contains(num - (diff << 1))) {
                ans++;
            }
        }
        return ans;
    }
}
