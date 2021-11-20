package com.jason.leetCode;

import java.util.Arrays;

public class LC594 {
    public static int findLHS(int[] nums) {
        int ans = 0;
        int left = 0, right = 1;
        Arrays.sort(nums);
        while (right < nums.length) {
            if (nums[right] - nums[left] == 1) {
                ans = Math.max(ans, right++ - left + 1);
            } else if (nums[right] == nums[left]) {
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,5,2,3,7};
        System.out.println(findLHS(nums));

    }
}
