package com.jason.leetCode;

import java.util.Arrays;

public class LC561 {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i+=2) {
            ans += nums[i];
        }
        return ans;
    }
}
