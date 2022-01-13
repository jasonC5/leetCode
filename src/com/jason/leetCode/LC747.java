package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC747 {
    public int dominantIndex(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE, idx = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        Arrays.sort(nums);
        return max >= 2 * nums[len - 2] ? idx : -1;
    }
}
