package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/8/4 9:16:04
 * @description
 */
public class LC1403 {
    public List<Integer> minSubsequence(int[] nums) {
        int target = Arrays.stream(nums).sum() >> 1;
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            ans.add(nums[i]);
            if (sum > target) {
                break;
            }
        }
        return ans;
    }
}
